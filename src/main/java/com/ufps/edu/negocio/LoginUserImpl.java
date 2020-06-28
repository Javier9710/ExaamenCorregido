package com.ufps.edu.negocio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ufps.edu.model.dao.IRolDao;
import com.ufps.edu.model.dao.IUsuarioDao;
import com.ufps.edu.model.entidades.Rol;
import com.ufps.edu.model.entidades.Usuario;

@Service
public class LoginUserImpl implements UserDetailsService {

	@Autowired
	private IUsuarioDao userdao;
	
	@Autowired
	private IRolDao rolDao;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		
		Optional<Usuario> opt = userdao.findByUsuario(name);
		Optional<Rol> rol=rolDao.findById(opt.get().getRol());
			List<GrantedAuthority> roles=new ArrayList<>();
			roles.add(new SimpleGrantedAuthority(rol.get().getDescripcion()));
			return new org.springframework.security.core.userdetails.User(name,opt.get().getClave() , roles);

	}

}