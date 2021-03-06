package io.openems.edge.ess.fenecon.commercial40;

import io.openems.edge.common.channel.OptionsEnum;

public enum ControlMode implements OptionsEnum {
	UNDEFINED(-1, "Undefined"), //
	REMOTE(1, "Remote"), //
	LOCAL(2, "Local"); //

	private final int value;
	private final String name;

	private ControlMode(int value, String name) {
		this.value = value;
		this.name = name;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public OptionsEnum getUndefined() {
		return UNDEFINED;
	}
}