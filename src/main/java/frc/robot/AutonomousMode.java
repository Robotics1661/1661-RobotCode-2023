package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.AutonomousDriveBackAndForthCommand;
import frc.robot.commands.AutonomousDriveBackCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

/* Just some convenient abstraction for autonomous modes */
public enum AutonomousMode {
    BALANCE(AutonomousDriveBackCommand::new, "Drive to charge station"),
    FORWARD_AND_BALANCE(AutonomousDriveBackAndForthCommand::new, "Drive to charge station, keep going, then drive back to charge station"),
    NONE(ds -> new InstantCommand(), "Do nothing")
    ;
    private final CommandBuilder builder;
    public final String description;
    AutonomousMode(CommandBuilder builder, String description) {
        this.builder = builder;
        this.description = description;
    }

    public CommandBase getCommand(DrivetrainSubsystem drivetrainSubsystem) {
        return this.builder.create(drivetrainSubsystem);
    }

    @FunctionalInterface
    interface CommandBuilder {
        public CommandBase create(DrivetrainSubsystem drivetrainSubsystem);
    }
}
