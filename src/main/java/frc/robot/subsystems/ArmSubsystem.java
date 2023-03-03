package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class ArmSubsystem extends SubsystemBase {

    private final TalonFX elbowMotor;
    private final TalonFX shoulderMotor;

    public ArmSubsystem() {
        elbowMotor = new TalonFX(ELBOW_MOTOR);
        shoulderMotor = new TalonFX(SHOULDER_MOTOR);
    }

    public void moveElbow(double speed) {
        speed = boundSpeed(speed);
        elbowMotor.set(TalonFXControlMode.PercentOutput, speed);
    }

    public void moveShoulder(double speed) {
        speed = boundSpeed(speed);
        shoulderMotor.set(TalonFXControlMode.PercentOutput, speed);
    }

    private double boundSpeed(double speed) {
        return Math.min(Math.max(-1, speed), 1);
    }
}
