// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.TurretConstants;
import frc.robot.Constants.YawConstants;
import frc.robot.subsystems.turret.TurretYawSubsystem;

public class SetTurretYaw extends Command {

  private TurretYawSubsystem turretYawSubsystem;
  private CommandXboxController controlController;
  public double yaw;

  /** Creates a new SetTurretYaw. */
  public SetTurretYaw(TurretYawSubsystem turretYaw, CommandXboxController controller) {
    this.turretYawSubsystem = turretYaw;
    this.controlController = controller;
    addRequirements(turretYawSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    yaw = turretYawSubsystem.getTurretYaw();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    double controllerInput = controlController.getRawAxis(0);
    if (controllerInput >= 0.02 || controllerInput <= -0.02) {
      yaw += controllerInput / YawConstants.YAW_CONTROL_REDUCTION;
    }
    if (yaw > YawConstants.YAW_MAX || yaw < YawConstants.YAW_MIN) {
      yaw = YawConstants.YAW_HOME;
    }
    //System.out.println(controllerInput);
    
    turretYawSubsystem.setYaw(yaw);
    //System.out.println(yaw);
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
