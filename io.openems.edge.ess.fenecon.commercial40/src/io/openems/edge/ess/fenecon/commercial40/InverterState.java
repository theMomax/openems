package io.openems.edge.ess.fenecon.commercial40;

import io.openems.edge.common.channel.OptionsEnum;

public enum InverterState implements OptionsEnum {
	UNDEFINED(-1, "Undefined"), //
	INIT(0, "Init"), //
	FAULT(2, "Fault"), //
	STOP(4, "Stop"), //
	STANDBY(8, "Standby"), //
	GRID_MONITOR(16, "Grid-Monitor"), //
	READY(32, "Ready"), //
	START(64, "Start"), //
	DEBUG(128, "Debug"); //

	private final int value;
	private final String name;

	private InverterState(int value, String name) {
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