package be.helmo.natamobile.reception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.helmo.natamobile.models.Role;
import be.helmo.natamobile.models.Session;
import be.helmo.natamobile.models.User;

//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class RUser extends IdentifiedModel
	  implements ReceptionObject<User> {

	private String fullName;

	private String email;

	private boolean admin = false;

	private String onlinePath;

	private List<RRole> roles;
	private List<RSession> sessions;

	private String password;

	public RUser() {
	}

	public RUser(User usr) {
		this.setId(usr.getId());
		this.fullName = usr.getFullName();
		this.email = usr.getEmail();
		this.admin = usr.isAdmin();
		this.password = usr.getPassword();
		this.onlinePath = usr.getPicture();
		this.roles = (usr.getRoles() != null)
			  ? convertRRoles(usr.getRoles())
			  : new ArrayList<RRole>();
		this.sessions = (usr.getSessions() != null)
			  ? convertRSessions(usr.getSessions())
			  : new ArrayList<RSession>();
	}

	private List<RSession> convertRSessions(List<Session> sessions) {
		List<RSession> rtn = new ArrayList<>();
		for (Session ses : sessions)
			rtn.add(new RSession(ses));
		return rtn;
	}

	private List<RRole> convertRRoles(List<Role> roles) {
		List<RRole> rtn = new ArrayList<>();
		for (Role role : roles)
			rtn.add(new RRole(role));
		return rtn;
	}

	@Override
	public User getModel() {
		User output = new User();
		output.setId(this.getId());
		output.setFullName(this.fullName);
		output.setEmail(this.email);
		output.setAdmin(this.admin);
		output.setPicture(this.onlinePath);
		output.setRoles(getRoles());
		output.setSessions(getSessions());
		return output;
	}

	public List<Role> getRoles() {
		List<Role> output = new ArrayList<>();
		for (RRole item : roles)
			output.add(item.getModel());
		return output;
	}

	public List<Session> getSessions() {
		if (sessions == null) {
			return Collections.emptyList();
		}
		List<Session> output = new ArrayList<>();
		for (RSession item : sessions)
			output.add(item.getModel());
		return output;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getOnlinePath() {
		return onlinePath;
	}

	public void setOnlinePath(String onlinePath) {
		this.onlinePath = onlinePath;
	}

	public void setRoles(List<RRole> roles) {
		this.roles = roles;
	}

	public void setSessions(List<RSession> sessions) {
		this.sessions = sessions;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
