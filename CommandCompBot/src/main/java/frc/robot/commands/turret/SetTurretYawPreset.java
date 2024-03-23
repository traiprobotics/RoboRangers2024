// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.TurretConstants;
import frc.robot.Constants.YawConstants;
import frc.robot.subsystems.turret.TurretYawSubsystem;

public class SetTurretYawPreset extends Command {

  private TurretYawSubsystem turretYawSubsystem;
  private double yaw;

  /** Creates a new SetTurretYaw. */
  public SetTurretYawPreset(TurretYawSubsystem turretYaw, double yaw) {
    this.turretYawSubsystem = turretYaw;
    this.yaw = yaw;
    addRequirements(turretYawSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    turretYawSubsystem.setYaw(yaw);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
