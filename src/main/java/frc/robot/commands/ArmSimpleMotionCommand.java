package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;
import java.util.function.DoubleSupplier;

public class ArmSimpleMotionCommand extends CommandBase {
    private final ArmSubsystem m_armSubsystem;

    private final DoubleSupplier m_rotationElbowSupplier;
    private final DoubleSupplier m_rotationShoulderSupplier;

    public ArmSimpleMotionCommand(ArmSubsystem armSubsystem,
                               DoubleSupplier rotationElbowSupplier,
                               DoubleSupplier rotationShoulderSupplier) {
        this.m_armSubsystem = armSubsystem;
        this.m_rotationElbowSupplier = rotationElbowSupplier;
        this.m_rotationShoulderSupplier = rotationShoulderSupplier;

        addRequirements(armSubsystem);
    }

    @Override
    public void execute() {
        m_armSubsystem.moveElbow(m_rotationElbowSupplier.getAsDouble());
        m_armSubsystem.moveShoulder(m_rotationShoulderSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        m_armSubsystem.moveElbow(0);
        m_armSubsystem.moveShoulder(0);
    }
}
