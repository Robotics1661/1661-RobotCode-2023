package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSubsystem;

import java.util.function.BooleanSupplier;

public class ClawCommand extends CommandBase {

    private final ClawSubsystem m_clawSubsystem;

    private final BooleanSupplier m_coneSupplier;
    private final BooleanSupplier m_cubeSupplier;
    private final BooleanSupplier m_releaseSupplier;
    private final BooleanSupplier m_offSupplier;

    public ClawCommand(ClawSubsystem clawSubsystem,
                               BooleanSupplier coneSupplier,
                               BooleanSupplier cubeSupplier,
                               BooleanSupplier releaseSupplier,
                               BooleanSupplier offSupplier) {
        this.m_clawSubsystem = clawSubsystem;

        this.m_coneSupplier = coneSupplier;
        this.m_cubeSupplier = cubeSupplier;
        this.m_releaseSupplier = releaseSupplier;
        this.m_offSupplier = offSupplier;

        addRequirements(clawSubsystem);
    }

    @Override
    public void execute() {
        if (m_coneSupplier.getAsBoolean()) {
            m_clawSubsystem.grabCone();
        } else if (m_cubeSupplier.getAsBoolean()) {
            m_clawSubsystem.grabCube();
        } else if (m_releaseSupplier.getAsBoolean()) {
            m_clawSubsystem.release();
        } else if (m_offSupplier.getAsBoolean()) {
            m_clawSubsystem.turnOff();
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_clawSubsystem.release();
        m_clawSubsystem.turnOff();
    }
}
