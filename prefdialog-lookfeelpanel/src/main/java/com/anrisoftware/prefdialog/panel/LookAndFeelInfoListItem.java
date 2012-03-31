package com.anrisoftware.prefdialog.panel;

import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.commons.lang.builder.EqualsBuilder;

class LookAndFeelInfoListItem {

	private final LookAndFeelInfo lookAndFeelInfo;

	public LookAndFeelInfoListItem(LookAndFeelInfo lookAndFeelInfo) {
		this.lookAndFeelInfo = lookAndFeelInfo;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LookAndFeelInfoListItem) {
			return equals((LookAndFeelInfoListItem) obj);

		}
		return false;
	}

	public LookAndFeelInfo getLookAndFeelInfo() {
		return lookAndFeelInfo;
	}

	private boolean equals(LookAndFeelInfoListItem that) {
		return new EqualsBuilder().append(lookAndFeelInfo.getName(),
				that.lookAndFeelInfo.getName()).isEquals();
	}

	@Override
	public int hashCode() {
		return lookAndFeelInfo.getName().hashCode();
	}
}
