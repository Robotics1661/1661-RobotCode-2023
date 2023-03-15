package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutonomousDriveBackAndForthCommand extends CommandBase {
    private double m_startTime;
    private DrivetrainSubsystem m_drivetrainSubsystem;

    public AutonomousDriveBackAndForthCommand(DrivetrainSubsystem drivetrainSubsystem) {
        this.m_startTime = Timer.getFPGATimestamp(); //just as a failsafe
        this.m_drivetrainSubsystem = drivetrainSubsystem;

        addRequirements(this.m_drivetrainSubsystem);
    }

    /*
     * Get time this command has been running, in seconds
     */
    private double getRunningTime() {
        return Timer.getFPGATimestamp() - m_startTime;
    }

    @Override
    public void initialize() {
        m_startTime = Timer.getFPGATimestamp();
        m_drivetrainSubsystem.calibrateGyro();
    }

    private boolean shouldMove() {
        return getRunningTime() < 3.5 + 4;
    }

    @Override
    public void execute() {
        if (!shouldMove()) return; //failsafe, should finalize
        // You can use `new ChassisSpeeds(...)` for robot-oriented movement instead of field-oriented movement
        if (getRunningTime() < 3.5) { //Drive to balance
            m_drivetrainSubsystem.drive(
                    ChassisSpeeds.fromFieldRelativeSpeeds(
                            .8,
                            0,
                            0,
                            m_drivetrainSubsystem.getGyroscopeRotation()
                    )
                    
            );
        } else if (getRunningTime() < 5.5) { //Drive forward and off
            m_drivetrainSubsystem.drive(
                    ChassisSpeeds.fromFieldRelativeSpeeds(
                            .8,
                            0,
                            0,
                            m_drivetrainSubsystem.getGyroscopeRotation()
                    )
                    
            );
        } else if (getRunningTime() < 7.5) { //Drive back onto charge station
            m_drivetrainSubsystem.drive(
                    ChassisSpeeds.fromFieldRelativeSpeeds(
                            -.8,
                            0,
                            0,
                            m_drivetrainSubsystem.getGyroscopeRotation()
                    )
                    
            );
        }
    }

    @Override
    public boolean isFinished() {
        return !shouldMove();
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrainSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }
}
