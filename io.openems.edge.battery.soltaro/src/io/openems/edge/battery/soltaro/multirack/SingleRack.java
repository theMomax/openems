package io.openems.edge.battery.soltaro.multirack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.openems.edge.bridge.modbus.api.ElementToChannelConverter;
import io.openems.edge.bridge.modbus.api.element.AbstractModbusElement;
import io.openems.edge.bridge.modbus.api.element.SignedWordElement;
import io.openems.edge.bridge.modbus.api.element.UnsignedWordElement;
import io.openems.edge.bridge.modbus.api.task.FC3ReadRegistersTask;
import io.openems.edge.bridge.modbus.api.task.Task;
import io.openems.edge.common.channel.Channel;
import io.openems.edge.common.channel.ChannelId;
import io.openems.edge.common.channel.Doc;
import io.openems.edge.common.channel.IntegerDoc;
import io.openems.edge.common.channel.Level;
import io.openems.edge.common.channel.Unit;
import io.openems.edge.common.taskmanager.Priority;

/**
 * Helper class that provides channels and channel ids for a multi rack channels
 * and ids are created dynamically depending on system configuration
 *
 */
public class SingleRack {

	private static final String KEY_VOLTAGE = "VOLTAGE";
	private static final String KEY_CURRENT = "CURRENT";
	private static final String KEY_CHARGE_INDICATION = "CHARGE_INDICATION";
	private static final String KEY_SOC = "SOC";
	private static final String KEY_SOH = "SOH";
	private static final String KEY_MAX_CELL_VOLTAGE_ID = "MAX_CELL_VOLTAGE_ID";
	private static final String KEY_MAX_CELL_VOLTAGE = "MAX_CELL_VOLTAGE";
	private static final String KEY_MIN_CELL_VOLTAGE_ID = "MIN_CELL_VOLTAGE_ID";
	private static final String KEY_MIN_CELL_VOLTAGE = "MIN_CELL_VOLTAGE";
	private static final String KEY_MAX_CELL_TEMPERATURE_ID = "MAX_CELL_TEMPERATURE_ID";
	private static final String KEY_MAX_CELL_TEMPERATURE = "MAX_CELL_TEMPERATURE";
	private static final String KEY_MIN_CELL_TEMPERATURE_ID = "MIN_CELL_TEMPERATURE_ID";
	private static final String KEY_MIN_CELL_TEMPERATURE = "MIN_CELL_TEMPERATURE";
	private static final String KEY_ALARM_LEVEL_2_CELL_DISCHA_TEMP_LOW = "ALARM_LEVEL_2_CELL_DISCHA_TEMP_LOW";
	private static final String KEY_ALARM_LEVEL_2_CELL_DISCHA_TEMP_HIGH = "ALARM_LEVEL_2_CELL_DISCHA_TEMP_HIGH";
	private static final String KEY_ALARM_LEVEL_2_GR_TEMPERATURE_HIGH = "ALARM_LEVEL_2_GR_TEMPERATURE_HIGH";
	private static final String KEY_ALARM_LEVEL_2_CELL_CHA_TEMP_LOW = "ALARM_LEVEL_2_CELL_CHA_TEMP_LOW";
	private static final String KEY_ALARM_LEVEL_2_CELL_CHA_TEMP_HIGH = "ALARM_LEVEL_2_CELL_CHA_TEMP_HIGH";
	private static final String KEY_ALARM_LEVEL_2_DISCHA_CURRENT_HIGH = "ALARM_LEVEL_2_DISCHA_CURRENT_HIGH";
	private static final String KEY_ALARM_LEVEL_2_TOTAL_VOLTAGE_LOW = "ALARM_LEVEL_2_TOTAL_VOLTAGE_LOW";
	private static final String KEY_ALARM_LEVEL_2_CELL_VOLTAGE_LOW = "ALARM_LEVEL_2_CELL_VOLTAGE_LOW";
	private static final String KEY_ALARM_LEVEL_2_CHA_CURRENT_HIGH = "ALARM_LEVEL_2_CHA_CURRENT_HIGH";
	private static final String KEY_ALARM_LEVEL_2_TOTAL_VOLTAGE_HIGH = "ALARM_LEVEL_2_TOTAL_VOLTAGE_HIGH";
	private static final String KEY_ALARM_LEVEL_2_CELL_VOLTAGE_HIGH = "ALARM_LEVEL_2_CELL_VOLTAGE_HIGH";
	private static final String KEY_ALARM_LEVEL_1_CELL_DISCHA_TEMP_LOW = "ALARM_LEVEL_1_CELL_DISCHA_TEMP_LOW";
	private static final String KEY_ALARM_LEVEL_1_CELL_DISCHA_TEMP_HIGH = "ALARM_LEVEL_1_CELL_DISCHA_TEMP_HIGH";
	private static final String KEY_ALARM_LEVEL_1_TOTAL_VOLTAGE_DIFF_HIGH = "ALARM_LEVEL_1_TOTAL_VOLTAGE_DIFF_HIGH";
	private static final String KEY_ALARM_LEVEL_1_CELL_VOLTAGE_DIFF_HIGH = "ALARM_LEVEL_1_CELL_VOLTAGE_DIFF_HIGH";
	private static final String KEY_ALARM_LEVEL_1_GR_TEMPERATURE_HIGH = "ALARM_LEVEL_1_GR_TEMPERATURE_HIGH";
	private static final String KEY_ALARM_LEVEL_1_CELL_TEMP_DIFF_HIGH = "ALARM_LEVEL_1_CELL_TEMP_DIFF_HIGH";
	private static final String KEY_ALARM_LEVEL_1_SOC_LOW = "ALARM_LEVEL_1_SOC_LOW";
	private static final String KEY_ALARM_LEVEL_1_CELL_CHA_TEMP_LOW = "ALARM_LEVEL_1_CELL_CHA_TEMP_LOW";
	private static final String KEY_ALARM_LEVEL_1_CELL_CHA_TEMP_HIGH = "ALARM_LEVEL_1_CELL_CHA_TEMP_HIGH";
	private static final String KEY_ALARM_LEVEL_1_DISCHA_CURRENT_HIGH = "ALARM_LEVEL_1_DISCHA_CURRENT_HIGH";
	private static final String KEY_ALARM_LEVEL_1_TOTAL_VOLTAGE_LOW = "ALARM_LEVEL_1_TOTAL_VOLTAGE_LOW";
	private static final String KEY_ALARM_LEVEL_1_CELL_VOLTAGE_LOW = "ALARM_LEVEL_1_CELL_VOLTAGE_LOW";
	private static final String KEY_ALARM_LEVEL_1_CHA_CURRENT_HIGH = "ALARM_LEVEL_1_CHA_CURRENT_HIGH";
	private static final String KEY_ALARM_LEVEL_1_TOTAL_VOLTAGE_HIGH = "ALARM_LEVEL_1_TOTAL_VOLTAGE_HIGH";
	private static final String KEY_ALARM_LEVEL_1_CELL_VOLTAGE_HIGH = "ALARM_LEVEL_1_CELL_VOLTAGE_HIGH";
	private static final String KEY_RUN_STATE = "RUN_STATE";
	private static final String KEY_FAILURE_INITIALIZATION = "FAILURE_INITIALIZATION";
	private static final String KEY_FAILURE_EEPROM = "FAILURE_EEPROM";
	private static final String KEY_FAILURE_INTRANET_COMMUNICATION = "FAILURE_INTRANET_COMMUNICATION";
	private static final String KEY_FAILURE_TEMPERATURE_SENSOR_CABLE = "FAILURE_TEMPERATURE_SENSOR_CABLE";
	private static final String KEY_FAILURE_BALANCING_MODULE = "FAILURE_BALANCING_MODULE";
	private static final String KEY_FAILURE_TEMPERATURE_PCB = "FAILURE_TEMPERATURE_PCB";
	private static final String KEY_FAILURE_GR_TEMPERATURE = "FAILURE_GR_TEMPERATURE";
	private static final String KEY_FAILURE_TEMP_SENSOR = "FAILURE_TEMP_SENSOR";
	private static final String KEY_FAILURE_TEMP_SAMPLING = "FAILURE_TEMP_SAMPLING";
	private static final String KEY_FAILURE_VOLTAGE_SAMPLING = "FAILURE_VOLTAGE_SAMPLING";
	private static final String KEY_FAILURE_LTC6803 = "FAILURE_LTC6803";
	private static final String KEY_FAILURE_CONNECTOR_WIRE = "FAILURE_CONNECTOR_WIRE";
	private static final String KEY_FAILURE_SAMPLING_WIRE = "FAILURE_SAMPLING_WIRE";

	private static final String VOLTAGE = "VOLTAGE";
	private static final String BATTERY = "BATTERY";
	private static final String RACK = "RACK";
	private static final String TEMPERATURE = "TEMPERATURE";

	public static final int VOLTAGE_SENSORS_PER_MODULE = 12;
	public static final int TEMPERATURE_SENSORS_PER_MODULE = 12;

	private static final String NUMBER_FORMAT = "%03d"; // creates string number with leading zeros
	private static final int VOLTAGE_ADDRESS_OFFSET = 0x800;
	private static final int TEMPERATURE_ADDRESS_OFFSET = 0xC00;

	private int rackNumber;
	private int numberOfSlaves;
	private int addressOffset;
	private MultiRack parent;
	private final Map<String, ChannelId> channelIds;
	private final Map<String, Channel<?>> channelMap;

	protected SingleRack(int racknumber, int numberOfSlaves, int addressOffset, MultiRack parent) {
		this.rackNumber = racknumber;
		this.numberOfSlaves = numberOfSlaves;
		this.addressOffset = addressOffset;
		this.parent = parent;
		channelIds = createChannelIdMap();
		channelMap = createChannelMap();
	}

	public Collection<Channel<?>> getChannels() {
		return channelMap.values();
	}

	private Map<String, Channel<?>> createChannelMap() {
		Map<String, Channel<?>> channels = new HashMap<>();

		channels.put(KEY_VOLTAGE, parent.addChannel(channelIds.get(KEY_VOLTAGE)));
		channels.put(KEY_CURRENT, parent.addChannel(channelIds.get(KEY_CURRENT)));
		channels.put(KEY_CHARGE_INDICATION, parent.addChannel(channelIds.get(KEY_CHARGE_INDICATION)));
		channels.put(KEY_SOC, parent.addChannel(channelIds.get(KEY_SOC)));
		channels.put(KEY_SOH, parent.addChannel(channelIds.get(KEY_SOH)));
		channels.put(KEY_MAX_CELL_VOLTAGE_ID, parent.addChannel(channelIds.get(KEY_MAX_CELL_VOLTAGE_ID)));
		channels.put(KEY_MAX_CELL_VOLTAGE, parent.addChannel(channelIds.get(KEY_MAX_CELL_VOLTAGE)));
		channels.put(KEY_MIN_CELL_VOLTAGE_ID, parent.addChannel(channelIds.get(KEY_MIN_CELL_VOLTAGE_ID)));
		channels.put(KEY_MIN_CELL_VOLTAGE, parent.addChannel(channelIds.get(KEY_MIN_CELL_VOLTAGE)));
		channels.put(KEY_MAX_CELL_TEMPERATURE_ID, parent.addChannel(channelIds.get(KEY_MAX_CELL_TEMPERATURE_ID)));
		channels.put(KEY_MAX_CELL_TEMPERATURE, parent.addChannel(channelIds.get(KEY_MAX_CELL_TEMPERATURE)));
		channels.put(KEY_MIN_CELL_TEMPERATURE_ID, parent.addChannel(channelIds.get(KEY_MIN_CELL_TEMPERATURE_ID)));
		channels.put(KEY_MIN_CELL_TEMPERATURE, parent.addChannel(channelIds.get(KEY_MIN_CELL_TEMPERATURE)));

		channels.put(KEY_ALARM_LEVEL_2_CELL_DISCHA_TEMP_LOW,
				parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_2_CELL_DISCHA_TEMP_LOW)));
		channels.put(KEY_ALARM_LEVEL_2_CELL_DISCHA_TEMP_HIGH,
				parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_2_CELL_DISCHA_TEMP_HIGH)));
		channels.put(KEY_ALARM_LEVEL_2_GR_TEMPERATURE_HIGH,
				parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_2_GR_TEMPERATURE_HIGH)));
		channels.put(KEY_ALARM_LEVEL_2_CELL_CHA_TEMP_LOW,
				parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_2_CELL_CHA_TEMP_LOW)));
		channels.put(KEY_ALARM_LEVEL_2_CELL_CHA_TEMP_HIGH,
				parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_2_CELL_CHA_TEMP_HIGH)));
		channels.put(KEY_ALARM_LEVEL_2_DISCHA_CURRENT_HIGH,
				parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_2_DISCHA_CURRENT_HIGH)));
		channels.put(KEY_ALARM_LEVEL_2_TOTAL_VOLTAGE_LOW,
				parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_2_TOTAL_VOLTAGE_LOW)));
		channels.put(KEY_ALARM_LEVEL_2_CELL_VOLTAGE_LOW,
				parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_2_CELL_VOLTAGE_LOW)));
		channels.put(KEY_ALARM_LEVEL_2_CHA_CURRENT_HIGH,
				parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_2_CHA_CURRENT_HIGH)));
		channels.put(KEY_ALARM_LEVEL_2_TOTAL_VOLTAGE_HIGH,
				parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_2_TOTAL_VOLTAGE_HIGH)));
		channels.put(KEY_ALARM_LEVEL_2_CELL_VOLTAGE_HIGH,
				parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_2_CELL_VOLTAGE_HIGH)));

		channels.put(KEY_ALARM_LEVEL_1_CELL_DISCHA_TEMP_LOW,
				parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_1_CELL_DISCHA_TEMP_LOW)));
		channels.put(KEY_ALARM_LEVEL_1_CELL_DISCHA_TEMP_HIGH,
				parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_1_CELL_DISCHA_TEMP_HIGH)));
		channels.put(KEY_ALARM_LEVEL_1_TOTAL_VOLTAGE_DIFF_HIGH,
				parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_1_TOTAL_VOLTAGE_DIFF_HIGH)));
		channels.put(KEY_ALARM_LEVEL_1_CELL_VOLTAGE_DIFF_HIGH,
				parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_1_CELL_VOLTAGE_DIFF_HIGH)));
		channels.put(KEY_ALARM_LEVEL_1_GR_TEMPERATURE_HIGH,
				parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_1_GR_TEMPERATURE_HIGH)));
		channels.put(KEY_ALARM_LEVEL_1_CELL_TEMP_DIFF_HIGH,
				parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_1_CELL_TEMP_DIFF_HIGH)));
		channels.put(KEY_ALARM_LEVEL_1_SOC_LOW, parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_1_SOC_LOW)));
		channels.put(KEY_ALARM_LEVEL_1_CELL_CHA_TEMP_LOW, parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_1_CELL_CHA_TEMP_LOW)));
		channels.put(KEY_ALARM_LEVEL_1_CELL_CHA_TEMP_HIGH, parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_1_CELL_CHA_TEMP_HIGH)));
		channels.put(KEY_ALARM_LEVEL_1_DISCHA_CURRENT_HIGH, parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_1_DISCHA_CURRENT_HIGH)));
		channels.put(KEY_ALARM_LEVEL_1_TOTAL_VOLTAGE_LOW, parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_1_TOTAL_VOLTAGE_LOW)));
		channels.put(KEY_ALARM_LEVEL_1_CELL_VOLTAGE_LOW, parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_1_CELL_VOLTAGE_LOW)));
		channels.put(KEY_ALARM_LEVEL_1_CHA_CURRENT_HIGH, parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_1_CHA_CURRENT_HIGH)));
		channels.put(KEY_ALARM_LEVEL_1_TOTAL_VOLTAGE_HIGH, parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_1_TOTAL_VOLTAGE_HIGH)));
		channels.put(KEY_ALARM_LEVEL_1_CELL_VOLTAGE_HIGH, parent.addChannel(channelIds.get(KEY_ALARM_LEVEL_1_CELL_VOLTAGE_HIGH)));

		channels.put(KEY_RUN_STATE, parent.addChannel(channelIds.get(KEY_RUN_STATE)));

		channels.put(KEY_FAILURE_INITIALIZATION, parent.addChannel(channelIds.get(KEY_FAILURE_INITIALIZATION)));
		channels.put(KEY_FAILURE_EEPROM, parent.addChannel(channelIds.get(KEY_FAILURE_EEPROM)));
		channels.put(KEY_FAILURE_INTRANET_COMMUNICATION,
				parent.addChannel(channelIds.get(KEY_FAILURE_INTRANET_COMMUNICATION)));
		channels.put(KEY_FAILURE_TEMPERATURE_SENSOR_CABLE,
				parent.addChannel(channelIds.get(KEY_FAILURE_TEMPERATURE_SENSOR_CABLE)));
		channels.put(KEY_FAILURE_BALANCING_MODULE, parent.addChannel(channelIds.get(KEY_FAILURE_BALANCING_MODULE)));
		channels.put(KEY_FAILURE_TEMPERATURE_PCB, parent.addChannel(channelIds.get(KEY_FAILURE_TEMPERATURE_PCB)));
		channels.put(KEY_FAILURE_GR_TEMPERATURE, parent.addChannel(channelIds.get(KEY_FAILURE_GR_TEMPERATURE)));
		channels.put(KEY_FAILURE_TEMP_SENSOR, parent.addChannel(channelIds.get(KEY_FAILURE_TEMP_SENSOR)));
		channels.put(KEY_FAILURE_TEMP_SAMPLING, parent.addChannel(channelIds.get(KEY_FAILURE_TEMP_SAMPLING)));
		channels.put(KEY_FAILURE_VOLTAGE_SAMPLING, parent.addChannel(channelIds.get(KEY_FAILURE_VOLTAGE_SAMPLING)));
		channels.put(KEY_FAILURE_LTC6803, parent.addChannel(channelIds.get(KEY_FAILURE_LTC6803)));
		channels.put(KEY_FAILURE_CONNECTOR_WIRE, parent.addChannel(channelIds.get(KEY_FAILURE_CONNECTOR_WIRE)));
		channels.put(KEY_FAILURE_SAMPLING_WIRE, parent.addChannel(channelIds.get(KEY_FAILURE_SAMPLING_WIRE)));

		// Cell voltages
		for (int i = 0; i < this.numberOfSlaves; i++) {
			for (int j = i * VOLTAGE_SENSORS_PER_MODULE; j < (i + 1) * VOLTAGE_SENSORS_PER_MODULE; j++) {
				String key = getSingleCellPrefix(j) + "_" + VOLTAGE;
				channels.put(key, parent.addChannel(channelIds.get(key)));
			}
		}

		// Cell temperatures
		for (int i = 0; i < this.numberOfSlaves; i++) {
			for (int j = i * TEMPERATURE_SENSORS_PER_MODULE; j < (i + 1) * TEMPERATURE_SENSORS_PER_MODULE; j++) {
				String key = getSingleCellPrefix(j) + "_" + TEMPERATURE;
				channels.put(key, parent.addChannel(channelIds.get(key)));
			}
		}

		return channels;
	}

	private Map<String, ChannelId> createChannelIdMap() {
		Map<String, ChannelId> map = new HashMap<String, ChannelId>();

		addEntry(map, KEY_VOLTAGE, new IntegerDoc().unit(Unit.MILLIVOLT));
		addEntry(map, KEY_CURRENT, new IntegerDoc().unit(Unit.MILLIAMPERE));

		addEntry(map, KEY_CHARGE_INDICATION, Doc.of(Enums.ChargeIndication.values()));
		addEntry(map, KEY_SOC, new IntegerDoc().unit(Unit.PERCENT));
		addEntry(map, KEY_SOH, new IntegerDoc().unit(Unit.PERCENT));
		addEntry(map, KEY_MAX_CELL_VOLTAGE_ID, new IntegerDoc().unit(Unit.NONE));
		addEntry(map, KEY_MAX_CELL_VOLTAGE, new IntegerDoc().unit(Unit.MILLIVOLT));
		addEntry(map, KEY_MIN_CELL_VOLTAGE_ID, new IntegerDoc().unit(Unit.NONE));
		addEntry(map, KEY_MIN_CELL_VOLTAGE, new IntegerDoc().unit(Unit.MILLIVOLT));
		addEntry(map, KEY_MAX_CELL_TEMPERATURE_ID, new IntegerDoc().unit(Unit.NONE));
		addEntry(map, KEY_MAX_CELL_TEMPERATURE, new IntegerDoc().unit(Unit.DEZIDEGREE_CELSIUS));
		addEntry(map, KEY_MIN_CELL_TEMPERATURE_ID, new IntegerDoc().unit(Unit.NONE));
		addEntry(map, KEY_MIN_CELL_TEMPERATURE, new IntegerDoc().unit(Unit.DEZIDEGREE_CELSIUS));

		addEntry(map, KEY_ALARM_LEVEL_2_CELL_DISCHA_TEMP_LOW,
				Doc.of(Level.FAULT).text("Rack" + this.rackNumber + " Cell Discharge Temperature Low Alarm Level 2")); // Bit
																														// 15
		addEntry(map, KEY_ALARM_LEVEL_2_CELL_DISCHA_TEMP_HIGH,
				Doc.of(Level.FAULT).text("Rack" + this.rackNumber + " Cell Discharge Temperature High Alarm Level 2")); // Bit
																														// 14
		addEntry(map, KEY_ALARM_LEVEL_2_GR_TEMPERATURE_HIGH,
				Doc.of(Level.FAULT).text("Rack" + this.rackNumber + " GR Temperature High Alarm Level 2")); // Bit
																											// 10
		addEntry(map, KEY_ALARM_LEVEL_2_CELL_CHA_TEMP_LOW,
				Doc.of(Level.FAULT).text("Rack" + this.rackNumber + " Cell Charge Temperature Low Alarm Level 2")); // Bit
																													// 7
		addEntry(map, KEY_ALARM_LEVEL_2_CELL_CHA_TEMP_HIGH,
				Doc.of(Level.FAULT).text("Rack" + this.rackNumber + " Cell Charge Temperature High Alarm Level 2")); // Bit
																														// 6
		addEntry(map, KEY_ALARM_LEVEL_2_DISCHA_CURRENT_HIGH,
				Doc.of(Level.FAULT).text("Rack" + this.rackNumber + " Discharge Current High Alarm Level 2")); // Bit
																												// 5
		addEntry(map, KEY_ALARM_LEVEL_2_TOTAL_VOLTAGE_LOW,
				Doc.of(Level.FAULT).text("Rack" + this.rackNumber + " Total Voltage Low Alarm Level 2")); // Bit
																											// 4
		addEntry(map, KEY_ALARM_LEVEL_2_CELL_VOLTAGE_LOW,
				Doc.of(Level.FAULT).text("Cluster 1 Cell Voltage Low Alarm Level 2")); // Bit 3
		addEntry(map, KEY_ALARM_LEVEL_2_CHA_CURRENT_HIGH,
				Doc.of(Level.FAULT).text("Rack" + this.rackNumber + " Charge Current High Alarm Level 2")); // Bit
																											// 2
		addEntry(map, KEY_ALARM_LEVEL_2_TOTAL_VOLTAGE_HIGH,
				Doc.of(Level.FAULT).text("Rack" + this.rackNumber + " Total Voltage High Alarm Level 2")); // Bit
																											// 1
		addEntry(map, KEY_ALARM_LEVEL_2_CELL_VOLTAGE_HIGH,
				Doc.of(Level.FAULT).text("Rack" + this.rackNumber + " Cell Voltage High Alarm Level 2")); // Bit
																											// 0

		addEntry(map, KEY_ALARM_LEVEL_1_CELL_DISCHA_TEMP_LOW,
				Doc.of(Level.WARNING).text("Rack" + this.rackNumber + " Cell Discharge Temperature Low Alarm Level 1")); // Bit
																															// 15
		addEntry(map, KEY_ALARM_LEVEL_1_CELL_DISCHA_TEMP_HIGH, Doc.of(Level.WARNING)
				.text("Rack" + this.rackNumber + " Cell Discharge Temperature High Alarm Level 1")); // Bit 14
		addEntry(map, KEY_ALARM_LEVEL_1_TOTAL_VOLTAGE_DIFF_HIGH,
				Doc.of(Level.WARNING).text("Rack" + this.rackNumber + " Total Voltage Diff High Alarm Level 1")); // Bit
																													// 13
		addEntry(map, KEY_ALARM_LEVEL_1_CELL_VOLTAGE_DIFF_HIGH,
				Doc.of(Level.WARNING).text("Rack" + this.rackNumber + " Cell Voltage Diff High Alarm Level 1")); // Bit
																													// 11
		addEntry(map, KEY_ALARM_LEVEL_1_GR_TEMPERATURE_HIGH,
				Doc.of(Level.WARNING).text("Rack" + this.rackNumber + " GR Temperature High Alarm Level 1")); // Bit
																												// 10
		addEntry(map, KEY_ALARM_LEVEL_1_CELL_TEMP_DIFF_HIGH,
				Doc.of(Level.WARNING).text("Rack" + this.rackNumber + " Cell temperature Diff High Alarm Level 1")); // Bit
																														// 9
		addEntry(map, KEY_ALARM_LEVEL_1_SOC_LOW,
				Doc.of(Level.WARNING).text("Rack" + this.rackNumber + " SOC Low Alarm Level 1")); // Bit 8
		addEntry(map, KEY_ALARM_LEVEL_1_CELL_CHA_TEMP_LOW,
				Doc.of(Level.WARNING).text("Rack" + this.rackNumber + " Cell Charge Temperature Low Alarm Level 1")); // Bit
																														// 7
		addEntry(map, KEY_ALARM_LEVEL_1_CELL_CHA_TEMP_HIGH,
				Doc.of(Level.WARNING).text("Rack" + this.rackNumber + " Cell Charge Temperature High Alarm Level 1")); // Bit
																														// 6
		addEntry(map, KEY_ALARM_LEVEL_1_DISCHA_CURRENT_HIGH,
				Doc.of(Level.WARNING).text("Rack" + this.rackNumber + " Discharge Current High Alarm Level 1")); // Bit
																													// 5
		addEntry(map, KEY_ALARM_LEVEL_1_TOTAL_VOLTAGE_LOW,
				Doc.of(Level.WARNING).text("Rack" + this.rackNumber + " Total Voltage Low Alarm Level 1")); // Bit
																											// 4
		addEntry(map, KEY_ALARM_LEVEL_1_CELL_VOLTAGE_LOW,
				Doc.of(Level.WARNING).text("Rack" + this.rackNumber + " Cell Voltage Low Alarm Level 1")); // Bit
																											// 3
		addEntry(map, KEY_ALARM_LEVEL_1_CHA_CURRENT_HIGH,
				Doc.of(Level.WARNING).text("Rack" + this.rackNumber + " Charge Current High Alarm Level 1")); // Bit
																												// 2
		addEntry(map, KEY_ALARM_LEVEL_1_TOTAL_VOLTAGE_HIGH,
				Doc.of(Level.WARNING).text("Rack" + this.rackNumber + " Total Voltage High Alarm Level 1")); // Bit
																												// 1
		addEntry(map, KEY_ALARM_LEVEL_1_CELL_VOLTAGE_HIGH,
				Doc.of(Level.WARNING).text("Rack" + this.rackNumber + " Cell Voltage High Alarm Level 1")); // Bit
																											// 0
		addEntry(map, KEY_RUN_STATE, Doc.of(Enums.ClusterRunState.values())); //

		addEntry(map, KEY_FAILURE_INITIALIZATION, Doc.of(Level.FAULT).text("Initialization failure")); // Bit
																										// 12
		addEntry(map, KEY_FAILURE_EEPROM, Doc.of(Level.FAULT).text("EEPROM fault")); // Bit 11
		addEntry(map, KEY_FAILURE_INTRANET_COMMUNICATION, Doc.of(Level.FAULT).text("Internal communication fault")); // Bit
																														// 10
		addEntry(map, KEY_FAILURE_TEMPERATURE_SENSOR_CABLE, Doc.of(Level.FAULT).text("Temperature sensor cable fault")); // Bit
																															// 9
		addEntry(map, KEY_FAILURE_BALANCING_MODULE, Doc.of(Level.FAULT).text("Balancing module fault")); // Bit
																											// 8
		addEntry(map, KEY_FAILURE_TEMPERATURE_PCB, Doc.of(Level.FAULT).text("Temperature PCB error")); // Bit 7
		addEntry(map, KEY_FAILURE_GR_TEMPERATURE, Doc.of(Level.FAULT).text("GR Temperature error")); // Bit 6
		addEntry(map, KEY_FAILURE_TEMP_SENSOR, Doc.of(Level.FAULT).text("Temperature sensor fault")); // Bit 5
		addEntry(map, KEY_FAILURE_TEMP_SAMPLING, Doc.of(Level.FAULT).text("Temperature sampling fault")); // Bit
																											// 4
		addEntry(map, KEY_FAILURE_VOLTAGE_SAMPLING, Doc.of(Level.FAULT).text("Voltage sampling fault")); // Bit
																											// 3
		addEntry(map, KEY_FAILURE_LTC6803, Doc.of(Level.FAULT).text("LTC6803 fault")); // Bit 2
		addEntry(map, KEY_FAILURE_CONNECTOR_WIRE, Doc.of(Level.FAULT).text("connector wire fault")); // Bit 1
		addEntry(map, KEY_FAILURE_SAMPLING_WIRE, Doc.of(Level.FAULT).text("sampling wire fault")); // Bit 0

		// Cell voltages formatted like: "RACK_1_BATTERY_000_VOLTAGE"
		for (int i = 0; i < this.numberOfSlaves; i++) {
			for (int j = i * VOLTAGE_SENSORS_PER_MODULE; j < (i + 1) * VOLTAGE_SENSORS_PER_MODULE; j++) {
				String key = getSingleCellPrefix(j) + "_" + VOLTAGE;
				addEntry(map, key, new IntegerDoc().unit(Unit.MILLIVOLT));
			}
		}
		// Cell temperatures formatted like : "RACK_1_BATTERY_000_TEMPERATURE"
		for (int i = 0; i < numberOfSlaves; i++) {
			for (int j = i * TEMPERATURE_SENSORS_PER_MODULE; j < (i + 1) * TEMPERATURE_SENSORS_PER_MODULE; j++) {
				String key = getSingleCellPrefix(j) + "_" + TEMPERATURE;
				map.put(key, new ChannelIdImpl(key, new IntegerDoc().unit(Unit.DEZIDEGREE_CELSIUS)));
			}
		}

		return map;
	}

	public Collection<Task> getTasks() {
		Collection<Task> tasks = new ArrayList<>();

		// State values
		tasks.add(new FC3ReadRegistersTask(this.addressOffset + 0x100, Priority.LOW, //
				parent.map(channelIds.get(KEY_VOLTAGE), getUWE(0x100), ElementToChannelConverter.SCALE_FACTOR_MINUS_1), //
				parent.map(channelIds.get(KEY_CURRENT), getUWE(0x101), ElementToChannelConverter.SCALE_FACTOR_MINUS_1), //
				parent.map(channelIds.get(KEY_CHARGE_INDICATION), getUWE(0x102)), //
				parent.map(channelIds.get(KEY_SOC), getUWE(0x103)), //
				parent.map(channelIds.get(KEY_SOH), getUWE(0x104)), //
				parent.map(channelIds.get(KEY_MAX_CELL_VOLTAGE_ID), getUWE(0x105)), //
				parent.map(channelIds.get(KEY_MAX_CELL_VOLTAGE), getUWE(0x106)), //
				parent.map(channelIds.get(KEY_MIN_CELL_VOLTAGE_ID), getUWE(0x107)), //
				parent.map(channelIds.get(KEY_MIN_CELL_VOLTAGE), getUWE(0x108)), //
				parent.map(channelIds.get(KEY_MAX_CELL_TEMPERATURE_ID), getUWE(0x109)), //
				parent.map(channelIds.get(KEY_MAX_CELL_TEMPERATURE), getUWE(0x10A)), //
				parent.map(channelIds.get(KEY_MIN_CELL_TEMPERATURE_ID), getUWE(0x10B)), //
				parent.map(channelIds.get(KEY_MIN_CELL_TEMPERATURE), getUWE(0x10C)) //
		));

		// Alarm levels
		tasks.add(new FC3ReadRegistersTask(this.addressOffset + 0x140, Priority.LOW, //
				parent.map(getUWE(0x140)) //
						.m(channelIds.get(KEY_ALARM_LEVEL_2_CELL_VOLTAGE_HIGH), 0) //
						.m(channelIds.get(KEY_ALARM_LEVEL_2_TOTAL_VOLTAGE_HIGH), 1) //
						.m(channelIds.get(KEY_ALARM_LEVEL_2_CHA_CURRENT_HIGH), 2) //
						.m(channelIds.get(KEY_ALARM_LEVEL_2_CELL_VOLTAGE_LOW), 3) //
						.m(channelIds.get(KEY_ALARM_LEVEL_2_TOTAL_VOLTAGE_LOW), 4) //
						.m(channelIds.get(KEY_ALARM_LEVEL_2_DISCHA_CURRENT_HIGH), 5) //
						.m(channelIds.get(KEY_ALARM_LEVEL_2_CELL_CHA_TEMP_HIGH), 6) //
						.m(channelIds.get(KEY_ALARM_LEVEL_2_CELL_CHA_TEMP_LOW), 7) //
						.m(channelIds.get(KEY_ALARM_LEVEL_2_GR_TEMPERATURE_HIGH), 10) //
						.m(channelIds.get(KEY_ALARM_LEVEL_2_CELL_DISCHA_TEMP_HIGH), 14) //
						.m(channelIds.get(KEY_ALARM_LEVEL_2_CELL_DISCHA_TEMP_LOW), 15) //
						.build(), //
				parent.map(getUWE(0x141)) //
						.m(channelIds.get(KEY_ALARM_LEVEL_1_CELL_VOLTAGE_HIGH), 0) //
						.m(channelIds.get(KEY_ALARM_LEVEL_1_TOTAL_VOLTAGE_HIGH), 1) //
						.m(channelIds.get(KEY_ALARM_LEVEL_1_CHA_CURRENT_HIGH), 2) //
						.m(channelIds.get(KEY_ALARM_LEVEL_1_CELL_VOLTAGE_LOW), 3) //
						.m(channelIds.get(KEY_ALARM_LEVEL_1_TOTAL_VOLTAGE_LOW), 4) //
						.m(channelIds.get(KEY_ALARM_LEVEL_1_DISCHA_CURRENT_HIGH), 5) //
						.m(channelIds.get(KEY_ALARM_LEVEL_1_CELL_CHA_TEMP_HIGH), 6) //
						.m(channelIds.get(KEY_ALARM_LEVEL_1_CELL_CHA_TEMP_LOW), 7) //
						.m(channelIds.get(KEY_ALARM_LEVEL_1_SOC_LOW), 8) //
						.m(channelIds.get(KEY_ALARM_LEVEL_1_CELL_TEMP_DIFF_HIGH), 9) //
						.m(channelIds.get(KEY_ALARM_LEVEL_1_GR_TEMPERATURE_HIGH), 10) //
						.m(channelIds.get(KEY_ALARM_LEVEL_1_CELL_VOLTAGE_DIFF_HIGH), 11) //
						.m(channelIds.get(KEY_ALARM_LEVEL_1_TOTAL_VOLTAGE_DIFF_HIGH), 13) //
						.m(channelIds.get(KEY_ALARM_LEVEL_1_CELL_DISCHA_TEMP_HIGH), 14) //
						.m(channelIds.get(KEY_ALARM_LEVEL_1_CELL_DISCHA_TEMP_LOW), 15) //
						.build(), //
				parent.map(channelIds.get(KEY_RUN_STATE), getUWE(0x142)) //
		) //
		);

		// Error Codes
		tasks.add(new FC3ReadRegistersTask(this.addressOffset + 0x185, Priority.LOW, //
				parent.map(getUWE(0x185)) //
						.m(channelIds.get(KEY_FAILURE_SAMPLING_WIRE), 0)//
						.m(channelIds.get(KEY_FAILURE_CONNECTOR_WIRE), 1)//
						.m(channelIds.get(KEY_FAILURE_LTC6803), 2)//
						.m(channelIds.get(KEY_FAILURE_VOLTAGE_SAMPLING), 3)//
						.m(channelIds.get(KEY_FAILURE_TEMP_SAMPLING), 4)//
						.m(channelIds.get(KEY_FAILURE_TEMP_SENSOR), 5)//
						.m(channelIds.get(KEY_FAILURE_GR_TEMPERATURE), 6)//
						.m(channelIds.get(KEY_FAILURE_TEMPERATURE_PCB), 7)//
						.m(channelIds.get(KEY_FAILURE_BALANCING_MODULE), 8)//
						.m(channelIds.get(KEY_FAILURE_TEMPERATURE_SENSOR_CABLE), 9)//
						.m(channelIds.get(KEY_FAILURE_INTRANET_COMMUNICATION), 10)//
						.m(channelIds.get(KEY_FAILURE_EEPROM), 11)//
						.m(channelIds.get(KEY_FAILURE_INITIALIZATION), 12)//
						.build() //
		));

		// Cell voltages
		for (int i = 0; i < this.numberOfSlaves; i++) {
			Collection<AbstractModbusElement<?>> elements = new ArrayList<>();
			for (int j = i * VOLTAGE_SENSORS_PER_MODULE; j < (i + 1) * VOLTAGE_SENSORS_PER_MODULE; j++) {
				String key = getSingleCellPrefix(j) + "_" + VOLTAGE;
				UnsignedWordElement uwe = getUWE(VOLTAGE_ADDRESS_OFFSET + j);
				AbstractModbusElement<?> ame = parent.map(channelIds.get(key), uwe);
				elements.add(ame);
			}
			tasks.add(new FC3ReadRegistersTask(
					this.addressOffset + VOLTAGE_ADDRESS_OFFSET + i * VOLTAGE_SENSORS_PER_MODULE, Priority.LOW,
					elements.toArray(new AbstractModbusElement<?>[0])));
		}

		// Cell temperatures
		for (int i = 0; i < this.numberOfSlaves; i++) {
			Collection<AbstractModbusElement<?>> elements = new ArrayList<>();
			for (int j = i * TEMPERATURE_SENSORS_PER_MODULE; j < (i + 1) * TEMPERATURE_SENSORS_PER_MODULE; j++) {
				String key = getSingleCellPrefix(j) + "_" + TEMPERATURE;
				SignedWordElement swe = new SignedWordElement(this.addressOffset + TEMPERATURE_ADDRESS_OFFSET + j);
				AbstractModbusElement<?> ame = parent.map(channelIds.get(key), swe);
				elements.add(ame);
			}
			tasks.add(new FC3ReadRegistersTask(
					this.addressOffset + TEMPERATURE_ADDRESS_OFFSET + i * TEMPERATURE_SENSORS_PER_MODULE, Priority.LOW,
					elements.toArray(new AbstractModbusElement<?>[0])));
		}

		return tasks;
	}

	public int getRackNumber() {
		return rackNumber;
	}

	public int getAddressOffset() {
		return addressOffset;
	}

	private ChannelId createChannelId(String key, Doc doc) {
		return new ChannelIdImpl(RACK + "_" + this.rackNumber + "_" + key, doc);
	}

	private void addEntry(Map<String, ChannelId> map, String key, Doc doc) {
		map.put(key, createChannelId(key, doc));
	}

	private String getSingleCellPrefix(int num) {
		return RACK + "_" + this.rackNumber + "_" + BATTERY + "_" + String.format(NUMBER_FORMAT, num);
	}

	private UnsignedWordElement getUWE(int address) {
		return new UnsignedWordElement(this.addressOffset + address);
	}
}
