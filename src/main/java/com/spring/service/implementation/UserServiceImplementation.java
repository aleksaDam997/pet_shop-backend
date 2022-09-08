<<<<<<< HEAD
package com.spring.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dto.UserRegistrationDto;
import com.spring.entity.ConfirmationToken;
import com.spring.entity.User;
import com.spring.entity.UserGroup;
import com.spring.repository.UserGroupRepository;
import com.spring.repository.UserRepository;
import com.spring.service.EmailSenderService;
import com.spring.service.UserService;
import com.spring.validator.EmailValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService{
	
	Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
	
	private final UserRepository userRepository;
	private final UserGroupRepository userGroupRepository;
	private final EmailValidator emailValidator;
	private final PasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenServiceImplementation confirmationTokenService;
	private final EmailSenderService emailSender;

	@Override
	public UserGroup SaveUserGroup(UserGroup userGroup) {
		return this.userGroupRepository.save(userGroup);
	}

	@Override
	public void addUserGroupToUser(String username, String groupName) {
		
		User user = this.userRepository.findByUsername(username).get();
		
		UserGroup userGroup = this.userGroupRepository.findByGroupName(groupName);
		
		user.getRoles().add(userGroup);
		
	}

	@Override
	public User getUser(String email) {
		return this.userRepository.findByEmail(email).get();
	}
	
	@Override
	public User getUserByUsername(String username) {
		return this.userRepository.findByUsername(username).get();
	}
	

	@Override
	public List<User> getUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = this.userRepository.findByEmail(email).get();
		
		if(user == null) {
			throw new UsernameNotFoundException("User with that username is not found..");
		}
		

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getGroupName())));
		
		logger.info("Username is: " + user.getUsername());
		logger.info(user.getPassword());
		logger.info(authorities.toString());
		
//		Radilo kod mene ali nije kod Cvijanovica
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
		
		//Radi kod Cvijanovica a cini mi se i kod mene
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}

	@Override
	public String register(UserRegistrationDto urd) {
		
		boolean isEmailValid = emailValidator.test(urd.getEmail());
		
		if(!isEmailValid) {
			throw new IllegalStateException("Email is not valid");
		}
		
		Collection<UserGroup> role = new ArrayList<>();
		UserGroup userGroup = new UserGroup();
		userGroup.setGroupName(urd.getRole());
		userGroup.setDescription("Simple user, with basic privilegies");
		role.add(userGroup);
		
		
		return this.signUpUser(new User(
				urd.getFirstName(),
				urd.getLastName(),
				urd.getUsername(),
				urd.getPassword(),
				urd.getEmail(),
				urd.getContact(),
				urd.getAddress(),
				role
				));
	}
	
	public String signUpUser(User user) {
		
		boolean userExists = this.userRepository.findByEmail(user.getEmail()).isPresent();
		
		if(userExists) {
			
			User userIsEnabled = this.userRepository.findByEmail(user.getEmail()).get();
			
			if(userIsEnabled.isEnabled()) {
				throw new IllegalStateException("Email allredy taken..");
			}
			
			String token = UUID.randomUUID().toString();
			
			ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), userIsEnabled);
			
			this.confirmationTokenService.saveConfirmationToken(confirmationToken);
			
			this.emailSender.send(userIsEnabled.getEmail(), this.buildEmail(userIsEnabled.getFirstName(), "http://localhost:8080/api/user/registration/confirm?token=" + token));
			
			return token;
			
		}
		
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);
		
		this.userRepository.save(user);
		
		String token = UUID.randomUUID().toString();
		
		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);
		
		this.confirmationTokenService.saveConfirmationToken(confirmationToken);
		
		this.emailSender.send(user.getEmail(), this.buildEmail(user.getFirstName(), "http://localhost:8080/api/user/registration/confirm?token=" + token));
		
		return token;
	}
	
	@Transactional
	public String confirmToken(String token) {
		
		ConfirmationToken confirmationToken = this.confirmationTokenService.getToken(token)
				.orElseThrow(() -> new IllegalStateException("Token not found.."));
		
		if(confirmationToken.getConfirmedAt() != null) {
			throw new IllegalStateException("Email allredy confirmed..");
		}
		
		LocalDateTime expiredAt = confirmationToken.getExpiresAt();
		
		if(expiredAt.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("Token has expired..");
		}
		
		this.confirmationTokenService.setConfirmedAt(token);
		this.enableUser(confirmationToken.getUser().getEmail());
		
		return "Cofirmed..";
		
		
	}
	
	private void enableUser(String email) {
		this.userRepository.enableUser(email);
	}

	private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
	
	private String sendEmail(User user) {
		String token = UUID.randomUUID().toString();
		
		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);
		
		this.confirmationTokenService.saveConfirmationToken(confirmationToken);
		
		this.emailSender.send(user.getEmail(), this.buildEmail(user.getFirstName(), "http://localhost:8080/api/user/registration/confirm?token=" + token));
		
		return token;
	}
}
=======
package com.spring.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dto.UserRegistrationDto;
import com.spring.entity.ConfirmationToken;
import com.spring.entity.User;
import com.spring.entity.UserGroup;
import com.spring.repository.UserGroupRepository;
import com.spring.repository.UserRepository;
import com.spring.service.EmailSenderService;
import com.spring.service.UserService;
import com.spring.validator.EmailValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService{
	
	Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
	
	private final UserRepository userRepository;
	private final UserGroupRepository userGroupRepository;
	private final EmailValidator emailValidator;
	private final PasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenServiceImplementation confirmationTokenService;
	private final EmailSenderService emailSender;

	@Override
	public UserGroup SaveUserGroup(UserGroup userGroup) {
		return this.userGroupRepository.save(userGroup);
	}

	@Override
	public void addUserGroupToUser(String username, String groupName) {
		
		User user = this.userRepository.findByUsername(username).get();
		
		UserGroup userGroup = this.userGroupRepository.findByGroupName(groupName);
		
		user.getRoles().add(userGroup);
		
	}

	@Override
	public User getUser(String email) {
		return this.userRepository.findByEmail(email).get();
	}
	
	@Override
	public User getUserByUsername(String username) {
		return this.userRepository.findByUsername(username).get();
	}
	

	@Override
	public List<User> getUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = this.userRepository.findByEmail(email).get();
		
		if(user == null) {
			throw new UsernameNotFoundException("User with that username is not found..");
		}
		

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getGroupName())));
		
		logger.info("Username is: " + user.getUsername());
		logger.info(user.getPassword());
		logger.info(authorities.toString());
		
//		Radilo kod mene ali nije kod Cvijanovica
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
		
		//Radi kod Cvijanovica a cini mi se i kod mene
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}

	@Override
	public String register(UserRegistrationDto urd) {
		
		boolean isEmailValid = emailValidator.test(urd.getEmail());
		
		if(!isEmailValid) {
			throw new IllegalStateException("Email is not valid");
		}
		
		Collection<UserGroup> role = new ArrayList<>();
		UserGroup userGroup = new UserGroup();
		userGroup.setGroupName(urd.getRole());
		userGroup.setDescription("Simple user, with basic privilegies");
		role.add(userGroup);
		
		
		return this.signUpUser(new User(
				urd.getFirstName(),
				urd.getLastName(),
				urd.getUsername(),
				urd.getPassword(),
				urd.getEmail(),
				urd.getContact(),
				urd.getAddress(),
				role
				));
	}
	
	public String signUpUser(User user) {
		
		boolean userExists = this.userRepository.findByEmail(user.getEmail()).isPresent();
		
		if(userExists) {
			
			User userIsEnabled = this.userRepository.findByEmail(user.getEmail()).get();
			
			if(userIsEnabled.isEnabled()) {
				throw new IllegalStateException("Email allredy taken..");
			}
			
			String token = UUID.randomUUID().toString();
			
			ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), userIsEnabled);
			
			this.confirmationTokenService.saveConfirmationToken(confirmationToken);
			
			this.emailSender.send(userIsEnabled.getEmail(), this.buildEmail(userIsEnabled.getFirstName(), "http://localhost:8080/api/user/registration/confirm?token=" + token));
			
			return token;
			
		}
		
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);
		
		this.userRepository.save(user);
		
		String token = UUID.randomUUID().toString();
		
		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);
		
		this.confirmationTokenService.saveConfirmationToken(confirmationToken);
		
		this.emailSender.send(user.getEmail(), this.buildEmail(user.getFirstName(), "http://localhost:8080/api/user/registration/confirm?token=" + token));
		
		return token;
	}
	
	@Transactional
	public String confirmToken(String token) {
		
		ConfirmationToken confirmationToken = this.confirmationTokenService.getToken(token)
				.orElseThrow(() -> new IllegalStateException("Token not found.."));
		
		if(confirmationToken.getConfirmedAt() != null) {
			throw new IllegalStateException("Email allredy confirmed..");
		}
		
		LocalDateTime expiredAt = confirmationToken.getExpiresAt();
		
		if(expiredAt.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("Token has expired..");
		}
		
		this.confirmationTokenService.setConfirmedAt(token);
		this.enableUser(confirmationToken.getUser().getEmail());
		
		return "Cofirmed..";
		
		
	}
	
	private void enableUser(String email) {
		this.userRepository.enableUser(email);
	}

	private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
	
	private String sendEmail(User user) {
		String token = UUID.randomUUID().toString();
		
		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);
		
		this.confirmationTokenService.saveConfirmationToken(confirmationToken);
		
		this.emailSender.send(user.getEmail(), this.buildEmail(user.getFirstName(), "http://localhost:8080/api/user/registration/confirm?token=" + token));
		
		return token;
	}
}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
