package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.SensorConverter;

import static frc.robot.Constants.*;

public class ArmSubsystem extends SubsystemBase {

    private final TalonFX elbowMotor;
    private final TalonFX shoulderMotor;
    private final SensorConverter shoulderConverter = SensorConverter.makeShoulder();
    private final SensorConverter elbowConverter = SensorConverter.makeElbow();

    public ArmSubsystem() {
        elbowMotor = new TalonFX(ELBOW_MOTOR);
        shoulderMotor = new TalonFX(SHOULDER_MOTOR);
    }

    public void dashboardInfo() {
        SmartDashboard.putNumber("elbowPos", elbowMotor.getSelectedSensorPosition());
        SmartDashboard.putNumber("shoulderPos", shoulderMotor.getSelectedSensorPosition());
        if (SmartDashboard.getBoolean("zeroDashboard", false))
            zeroMotorSensors();
        SmartDashboard.putBoolean("zeroDashboard", false);
        getExtensionInches(
            shoulderMotor.getSelectedSensorPosition(),
            elbowMotor.getSelectedSensorPosition()
        );
    }

    public void zeroMotorSensors() {
        elbowMotor.setSelectedSensorPosition(0); //max: 186977.000000
        shoulderMotor.setSelectedSensorPosition(0);
    }

    public void moveElbow(double speed) {
        speed = boundSpeed(speed);
        elbowMotor.set(TalonFXControlMode.PercentOutput, speed);
    }

    private boolean gettingToSafety = false;

    public void moveShoulder(double speed, boolean doEscape) {
        int hardLimit = 50; // must always be > 30
        int escapeZone = 5; // and extra 10 -> won't control smaller than 40
        //will escape to 40 if it gets to 30
        double shoulderDegrees = shoulderConverter.toRealDegrees(shoulderMotor.getSelectedSensorPosition());
        int softLimit = hardLimit + escapeZone;


        if (shoulderDegrees < softLimit && doEscape) {
            speed = 0;
        }
        if (doEscape) {
            boolean currentlySafeSafe = shoulderDegrees > softLimit+10;
            boolean currentlyEndanger = shoulderDegrees <= softLimit+10;
            boolean mustEscape = false;
            if (gettingToSafety) {
                if (currentlySafeSafe) {
                    gettingToSafety = false;
                } else {
                    mustEscape = true;
                }
            } else if (currentlyEndanger) {
                gettingToSafety = true;
                mustEscape = true;
            }
            if (mustEscape) {
                speed = -0.15;
            }
            SmartDashboard.putBoolean("currentlySafeSafe", currentlySafeSafe);
            SmartDashboard.putBoolean("currentlyEndanger", currentlyEndanger);
            SmartDashboard.putBoolean("mustEscape", mustEscape);
        }


        speed = boundSpeed(speed);
        shoulderMotor.set(TalonFXControlMode.PercentOutput, speed);
    }

    private double boundSpeed(double speed) {
        return Math.min(Math.max(-1, speed), 1);
    }

    private double getExtensionInches(double shoulderSensorValue, double elbowSensorValue) {
        double shoulderDegrees = shoulderConverter.toRealDegrees(shoulderSensorValue);
        double elbowDegreesRelative = elbowConverter.toRealDegrees(elbowSensorValue);

        double elbowDegrees = elbowDegreesRelative + shoulderDegrees;

        SmartDashboard.putNumber("shoulderDegrees", shoulderDegrees);
        SmartDashboard.putNumber("elbowDegreesRelative", elbowDegreesRelative);
        SmartDashboard.putNumber("elbowDegrees", elbowDegrees);

        return 0;
    }

    /*private static int sensor_90 = 29467;
    private static int sensor_135 = 123362;
    private static int _45_degrees_as_sens = sensor_135 - sensor_90;

    private static double toSensorUnits(double degrees) {
        return degrees * _45_degrees_as_sens / 45.0d;
    }

    private static double toDegrees(double sensorUnits) {
        return sensorUnits * 45.0d / _45_degrees_as_sens;
    }

    private static double realDegreesFromLeft(double reportedSensorUnits) {
//        double sensor_90_as_degrees = toDegrees(sensor_90);
//        double reported_as_degrees = toDegrees(reportedSensorUnits);
//        double real_deg = reported_as_degrees - sensor_90_as_degrees + 90;
//        return real_deg;
        return toDegrees(reportedSensorUnits - sensor_90) + 90;
    }*/
}
