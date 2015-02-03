package bankapp.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import bankapp.Objects.ApplicationContextUtils;
import bankapp.Objects.BankUser;
import bankapp.Objects.Role;
 
public class MyUserDetailsService implements UserDetailsService {

	@SuppressWarnings("deprecation")
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException 
			{
		System.out.println(username);
		System.out.println("==================================================================");
		//UserDetails user = new User(username, "password", true, true, true, true, new GrantedAuthority[]{ )
		if(ApplicationContextUtils.getBankUserJDBCTemplate().checkUserReturned(username))
		{		
			BankUser b=ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserName(username);
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for(Role r:b.getRoles())	
				authorities.add(new SimpleGrantedAuthority(r.getName()));		
			return	new User(username, b.getPassword(), true, true, true, true,authorities);
		}
		else
		{
			throw new UsernameNotFoundException("Username Does not Exsist");
		}
	}
 
}
