/**
 * (c) 2015 ADMB. All rights reserved.
 */
package be.provikmo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author infglef
 *
 */
public class IconMenuItem implements Serializable {
	private static final long serialVersionUID = 1L;

	/** The id. */
	private String id;

	/** The security roles that you must have to be able to view this menu item. */
	private String roles = "";

	/** The jsf action outcome. */
	private String action;

	/** Extra parameters that should be added to a link. */
	private Map<String, String> parameters;

	/**
	 * The message key (in messages property file). Used for the label of the
	 * link.
	 */
	private String messageKey;

	/** The description key. Used for the alt attribute of the link. */
	private String descriptionKey;

	/**
	 * Should this menu item be recorded by the MenuActionTrackingBean. If
	 * transient, will not appear in the breadcrumb if clicked on.
	 */
	private boolean transientMenuItem = false;

	/** Key value (in messages property file) for an externatl link. */
	private String externalLocationKey;

	/** The external location link. */
	private String externalLocationLink;

	/** The anchor target as in <a href="" target=""/>. */
	private String anchorTarget = "_self";

	/** The children menu items. */
	private List<IconMenuItem> children = new ArrayList<IconMenuItem>(0);

	/** The parent menu item. */
	private IconMenuItem parent;

	/** show or hide this menu item. */
	private boolean hidden = false;

	private String iconLocation;

	private Integer weight;

	/**
	 * Instantiates a new menu item.
	 */
	public IconMenuItem() {
	}

	/**
	 * Instantiates a new menu item.
	 * 
	 * @param iconLocation
	 *            the icon location
	 * @param weight
	 *            the weight of a menuItem
	 */
	public IconMenuItem(String iconLocation, Integer weight) {
		this.iconLocation = iconLocation;
		this.weight = weight;
	}

	/**
	 * Instantiates a new menu item.
	 * 
	 * @param messageKey
	 *            the message key
	 * @param iconLocation
	 *            the icon location
	 * @param weight
	 *            the weight of a menuItem
	 */
	public IconMenuItem(String messageKey, String iconLocation, Integer weight) {
		this.iconLocation = iconLocation;
		this.weight = weight;
		this.messageKey = messageKey;
	}

	/**
	 * Instantiates a new menu item.
	 * 
	 * @param messageKey
	 *            the message key
	 * @param action
	 *            the action
	 * @param iconLocation
	 *            the icon location
	 * @param weight
	 *            the weight of a menuItem
	 */
	public IconMenuItem(String messageKey, String action, String iconLocation, Integer weight) {
		this.iconLocation = iconLocation;
		this.weight = weight;
		this.messageKey = messageKey;
		this.action = action;
	}

	/**
	 * Instantiates a new menu item.
	 * 
	 * @param messageKey
	 *            the message key
	 * @param descriptionKey
	 *            the description key
	 * @param action
	 *            the action
	 * @param iconLocation
	 *            the icon location
	 * @param weight
	 *            the weight of a menuItem
	 */
	public IconMenuItem(String messageKey, String descriptionKey, String action, String iconLocation, Integer weight) {
		this.iconLocation = iconLocation;
		this.weight = weight;
		this.messageKey = messageKey;
		this.descriptionKey = descriptionKey;
		this.action = action;
	}

	/**
	 * Instantiates a new menu item.
	 * 
	 * @param messageKey
	 *            the message key
	 * @param descriptionKey
	 *            the description key
	 * @param action
	 *            the action
	 * @param roles
	 *            the user roles
	 * @param iconLocation
	 *            the icon location
	 * @param weight
	 *            the weight of a menuItem
	 */
	public IconMenuItem(String messageKey, String descriptionKey, String action, String roles, String iconLocation,
		Integer weight) {
		this.iconLocation = iconLocation;
		this.weight = weight;
		this.messageKey = messageKey;
		this.descriptionKey = descriptionKey;
		this.action = action;
		this.roles = roles;
	}

	/**
	 * @return the iconLocation
	 */
	public String getIconLocation() {
		return iconLocation;
	}

	/**
	 * @param iconLocation
	 *            the iconLocation to set
	 */
	public void setIconLocation(String iconLocation) {
		this.iconLocation = iconLocation;
	}

	/**
	 * @return the weight
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the roles
	 */
	public String getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the parameters
	 */
	public Map<String, String> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the messageKey
	 */
	public String getMessageKey() {
		return messageKey;
	}

	/**
	 * @param messageKey
	 *            the messageKey to set
	 */
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	/**
	 * @return the descriptionKey
	 */
	public String getDescriptionKey() {
		return descriptionKey;
	}

	/**
	 * @param descriptionKey
	 *            the descriptionKey to set
	 */
	public void setDescriptionKey(String descriptionKey) {
		this.descriptionKey = descriptionKey;
	}

	/**
	 * @return the transientMenuItem
	 */
	public boolean isTransientMenuItem() {
		return transientMenuItem;
	}

	/**
	 * @param transientMenuItem
	 *            the transientMenuItem to set
	 */
	public void setTransientMenuItem(boolean transientMenuItem) {
		this.transientMenuItem = transientMenuItem;
	}

	/**
	 * @return the externalLocationKey
	 */
	public String getExternalLocationKey() {
		return externalLocationKey;
	}

	/**
	 * @param externalLocationKey
	 *            the externalLocationKey to set
	 */
	public void setExternalLocationKey(String externalLocationKey) {
		this.externalLocationKey = externalLocationKey;
	}

	/**
	 * @return the externalLocationLink
	 */
	public String getExternalLocationLink() {
		return externalLocationLink;
	}

	/**
	 * @param externalLocationLink
	 *            the externalLocationLink to set
	 */
	public void setExternalLocationLink(String externalLocationLink) {
		this.externalLocationLink = externalLocationLink;
	}

	/**
	 * @return the anchorTarget
	 */
	public String getAnchorTarget() {
		return anchorTarget;
	}

	/**
	 * @param anchorTarget
	 *            the anchorTarget to set
	 */
	public void setAnchorTarget(String anchorTarget) {
		this.anchorTarget = anchorTarget;
	}

	/**
	 * @return the children
	 */
	public List<IconMenuItem> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<IconMenuItem> children) {
		this.children = children;
	}

	/**
	 * @return the parent
	 */
	@JsonIgnore
	public IconMenuItem getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(IconMenuItem parent) {
		this.parent = parent;
	}

	/**
	 * @return the hidden
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * @param hidden
	 *            the hidden to set
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public void addChild(IconMenuItem child) {
		children.add(child);
	}

	public boolean hasChildren() {
		return !CollectionUtils.isEmpty(children);
	}

}
