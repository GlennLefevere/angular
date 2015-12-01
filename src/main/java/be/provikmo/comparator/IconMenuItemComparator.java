/**
 * (c) 2015 ADMB. All rights reserved.
 */
package be.provikmo.comparator;

import java.util.Comparator;

import be.provikmo.model.IconMenuItem;

/**
 * @author infglef
 *
 */
public class IconMenuItemComparator implements Comparator<IconMenuItem> {

	/** {@inheritDoc} */
	@Override
	public int compare(IconMenuItem o1, IconMenuItem o2) {
		return o1.getWeight().compareTo(o2.getWeight());
	}

}
