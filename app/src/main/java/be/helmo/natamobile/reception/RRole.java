package be.helmo.natamobile.reception;

import be.helmo.natamobile.models.Role;

public class RRole extends IdentifiedModel
	  implements ReceptionObject<Role> {
	
	private String name;
	
	private String description;
	
	public RRole() {
	}
	
	public RRole(String name, String desc) {
		this.name = name;
		this.description = desc;
	}
	
	public RRole(Role role) {
		this.setId(role.getId());
		this.name = role.getName();
		this.description = role.getDescription();
	}
	
	@Override
	public Role getModel() {
		Role rtn = new Role();
		rtn.setName(this.name);
		rtn.setDescription(this.description);
		return rtn;
	}
}
