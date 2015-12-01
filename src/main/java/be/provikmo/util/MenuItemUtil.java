/**
 * (c) 2015 ADMB. All rights reserved.
 */
package be.provikmo.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import be.admb.alm.application.ApplicationMetaModel;
import be.admb.alm.application.EntryPoint;
import be.admb.alm.application.Grant;
import be.admb.alm.registration.HazelcastRegister;
import be.provikmo.comparator.IconMenuItemComparator;
import be.provikmo.model.IconMenuItem;

/**
 * @author infglef
 *
 */
public final class MenuItemUtil {
	private static final String ROLE_PREFIX = "ROLE_";

	/**
	 * Should be abstract method in a base class.
	 * To be
	 * 
	 * @param metaModel
	 *            the metaModel
	 * @return is process ApplicationMetaModel
	 */
	public static boolean isProcessApplicationMetaModel(ApplicationMetaModel metaModel) {
		return "LIBERO".equalsIgnoreCase(metaModel.getGroupID())
			&& "PROVIKMO".equalsIgnoreCase(metaModel.getCompanyID())
			&& ApplicationMetaModel.Type.CLIENT.equals(metaModel.getType());
	}

	// PRIVATE METHODS
	/**
	 * register extra MenuItems buildup using the ApplicationMetaModel information
	 */
	public static List<IconMenuItem> registerApplicationMetaModelMenuItems(HazelcastRegister register) {
		List<IconMenuItem> menuItems = new ArrayList<IconMenuItem>();

		// and find them in the Hazelcast Register
		Collection<ApplicationMetaModel> appMetaModels = register.getAll();

		Map<String, IconMenuItem> categoryMenuItemsMap = discoverCategories();
		for (ApplicationMetaModel metaModel : appMetaModels) {
			if (isProcessApplicationMetaModel(metaModel)) {
				List<EntryPoint> entryPoints = metaModel.getEntryPoints();
				if (entryPoints.size() == 1) {
					IconMenuItem mainItem = null;
					String cat = entryPoints.get(0).getCategory();

					// verified if there was an optional Category defined otherwise use the metaModel DisplayName
					if (StringUtils.isEmpty(cat)) {
						cat = metaModel.getDisplayName();
					}

					mainItem = new IconMenuItem(cat, metaModel.getImage(), metaModel.getWeight());
					EntryPoint entryPoint = entryPoints.get(0);
					String role = ROLE_PREFIX + entryPoint.findGrants(Grant.Type.ENTRYPOINT).get(0).getGrant();
					mainItem.setRoles(role);
					mainItem.setExternalLocationLink(entryPoint.getEntryPointUrl());
					if (entryPoint.getTarget() != null) {
						mainItem.setAnchorTarget(entryPoint.getTarget());
					}
					categoryMenuItemsMap.put(cat, mainItem);
				} else {
					for (EntryPoint entryPoint : entryPoints) {

						IconMenuItem categoryMenu = null;
						IconMenuItem childMenuItem = null;
						String cat = entryPoint.getCategory();

						// verified if there was an optional Category defined otherwise use the metaModel DisplayName
						if (StringUtils.isEmpty(cat)) {
							cat = metaModel.getDisplayName();
						}

						if (!categoryMenuItemsMap.containsKey(cat)) {
							categoryMenu =
								new IconMenuItem(cat, cat, "menu", metaModel.getImage(), metaModel.getWeight());
							categoryMenuItemsMap.put(cat, categoryMenu);
						} else {
							categoryMenu = categoryMenuItemsMap.get(cat);
						}

						childMenuItem = new IconMenuItem(entryPoint.getDisplayName(), "", entryPoint.getWeight());
						String role = ROLE_PREFIX + entryPoint.findGrants(Grant.Type.ENTRYPOINT).get(0).getGrant();
						childMenuItem.setRoles(role);
						childMenuItem.setExternalLocationLink(entryPoint.getEntryPointUrl());
						categoryMenu.addChild(childMenuItem);
					}
				}
			}
		}

		addAndSortMenuItems(categoryMenuItemsMap, menuItems);

		Collections.sort(menuItems, new IconMenuItemComparator());

		return menuItems;
	}

	private static void addAndSortMenuItems(Map<String, IconMenuItem> categoryMenuItemsMap,
		List<IconMenuItem> menuItems) {
		for (IconMenuItem mi : categoryMenuItemsMap.values()) {
			if (mi.hasChildren()) {
				Collections.sort(mi.getChildren(), new IconMenuItemComparator());
				for (IconMenuItem menuItem : mi.getChildren()) {
					menuItem.setParent(null);
				}
			}
			menuItems.add(mi);
		}
	}

	/**
	 * @return a map of already available Category (root) MenuItems
	 */
	private static Map<String, IconMenuItem> discoverCategories() {
		return new ConcurrentHashMap<String, IconMenuItem>();
	}

}
