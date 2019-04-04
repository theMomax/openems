import { DefaultTypes } from "../service/defaulttypes";
import { Utils } from "../service/utils";

export class CurrentData {

    public readonly summary: DefaultTypes.Summary;

    constructor(
        public readonly channel: { [channelAddress: string]: any } = {}
    ) {
        this.summary = this.getSummary(channel);
    }

    private getSummary(c: { [channelAddress: string]: any }): DefaultTypes.Summary {
        let result: DefaultTypes.Summary = {
            system: {
                totalPower: null,
            }, storage: {
                soc: null,
                chargeActivePower: null, // sum of chargeActivePowerAC and chargeActivePowerDC
                chargeActivePowerAC: null,
                chargeActivePowerDC: null,
                maxChargeActivePower: null,
                dischargeActivePower: null, // equals dischargeActivePowerAC
                dischargeActivePowerAC: null,
                dischargeActivePowerDC: null,
                maxDischargeActivePower: null,
                powerRatio: null,
                maxApparentPower: null
            }, production: {
                hasDC: false,
                powerRatio: null,
                activePower: null, // sum of activePowerAC and activePowerDC
                activePowerL1: null,
                activePowerL2: null,
                activePowerL3: null,
                activePowerAC: null,
                activePowerDC: null,
                maxActivePower: null
            }, grid: {
                gridMode: null,
                powerRatio: null,
                buyActivePower: null,
                maxBuyActivePower: null,
                sellActivePower: null,
                maxSellActivePower: null
            }, consumption: {
                powerRatio: null,
                activePower: null
            }
        };

        {
            /*
             * Storage
             * > 0 => Discharge
             * < 0 => Charge
             */
            result.storage.soc = c['_sum/EssSoc'];
            const essActivePower: number = c['_sum/EssActivePower'];
            result.storage.activePowerL1 = Math.abs(c['ess0/ActivePowerL1']);
            result.storage.activePowerL2 = Math.abs(c['ess0/ActivePowerL2']);
            result.storage.activePowerL3 = Math.abs(c['ess0/ActivePowerL3']);
            result.storage.maxApparentPower = c['_sum/MaxApparentPower'];
            if (!result.storage.maxApparentPower) {
                result.storage.maxApparentPower = 5000;
            }
            result.storage.chargeActivePowerDC = c['_sum/ProductionDcActualPower'];
            if (essActivePower == null) {
                // keep 'null'
            } else if (essActivePower > 0) {
                result.storage.chargeActivePowerAC = 0;
                result.storage.dischargeActivePowerAC = essActivePower;
                // TODO: should consider DC-Power of ratio
                result.storage.powerRatio = Utils.orElse(Utils.divideSafely(essActivePower, result.storage.maxApparentPower), 0);
            } else {
                result.storage.chargeActivePowerAC = Utils.multiplySafely(essActivePower, -1);
                result.storage.dischargeActivePowerAC = 0;
                result.storage.powerRatio = Utils.orElse(Utils.divideSafely(essActivePower, result.storage.maxApparentPower), 0);
            }
            result.storage.chargeActivePower = Utils.addSafely(result.storage.chargeActivePowerAC, result.storage.chargeActivePowerDC);
            result.storage.dischargeActivePower = result.storage.dischargeActivePowerAC;
        }

        {
            /*
             * Grid
             * > 0 => Buy from grid
             * < 0 => Sell to grid
             */
            const gridActivePower: number = c['_sum/GridActivePower'];
            result.grid.maxBuyActivePower = c['_sum/GridMaxActivePower'];
            result.grid.activePowerL1 = Math.abs(c['meter0/ActivePowerL1']);
            result.grid.activePowerL2 = Math.abs(c['meter0/ActivePowerL2']);
            result.grid.activePowerL3 = Math.abs(c['meter0/ActivePowerL3']);
            if (!result.grid.maxBuyActivePower) {
                result.grid.maxBuyActivePower = 5000;
            }
            result.grid.maxSellActivePower = c['_sum/GridMinActivePower'] * -1;
            if (!result.grid.maxSellActivePower) {
                result.grid.maxSellActivePower = -5000;
            }
            result.grid.gridMode = c['_sum/GridMode'];
            if (gridActivePower > 0) {
                result.grid.sellActivePower = 0;
                result.grid.buyActivePower = gridActivePower;
                result.grid.powerRatio = Utils.orElse(Utils.divideSafely(gridActivePower, result.grid.maxBuyActivePower), 0);
            } else {
                result.grid.sellActivePower = gridActivePower * -1;
                result.grid.buyActivePower = 0;
                result.grid.powerRatio = Utils.orElse(Utils.divideSafely(gridActivePower, result.grid.maxSellActivePower), 0);
            }
        }

        {
            /*
             * Production
             */
            result.production.activePowerAC = c['_sum/ProductionAcActivePower'];
            result.production.activePower = c['_sum/ProductionActivePower'];
            result.production.activePowerL1 = c['meter1/ActivePowerL1'];
            result.production.activePowerL2 = c['meter1/ActivePowerL2'];
            result.production.activePowerL3 = c['meter1/ActivePowerL3'];
            result.production.maxActivePower = c['_sum/ProductionMaxActivePower'];
            if (!result.production.maxActivePower) {
                result.production.maxActivePower = 10000;
            }
            result.production.powerRatio = Utils.orElse(Utils.divideSafely(result.production.activePower, result.production.maxActivePower), 0);
            result.production.activePowerDC = c['_sum/ProductionDcActualPower'];
        }

        {
            /*
             * Consumption
             */
            result.consumption.activePower = Utils.orElse(c['_sum/ConsumptionActivePower'], 0);
            let consumptionMaxActivePower = c['_sum/ConsumptionMaxActivePower'];
            if (!consumptionMaxActivePower) {
                consumptionMaxActivePower = 10000;
            }
            result.consumption.powerRatio = Utils.orElse(Utils.divideSafely(result.consumption.activePower, consumptionMaxActivePower), 0);
            if (result.consumption.powerRatio < 0) {
                result.consumption.powerRatio = 0;
            }
        }

        {
            /*
             * Total
             */
            result.system.totalPower = Math.max(
                // Productions
                result.grid.buyActivePower
                + (result.production.activePower > 0 ? result.production.activePower : 0)
                + result.storage.dischargeActivePowerAC,
                + (result.consumption.activePower < 0 ? result.consumption.activePower * -1 : 0),
                // Consumptions
                result.grid.sellActivePower
                + (result.production.activePower < 0 ? result.production.activePower * -1 : 0)
                + result.storage.chargeActivePowerAC,
                + (result.consumption.activePower > 0 ? result.consumption.activePower : 0)
            );
        }
        return result;
    }

}