// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IntakeSubsystem;

public class RunBackIntake extends Command {
  /** Creates a new RunIntake. */

  private IntakeSubsystem intakeSubsystem;

  double speed;

  public RunBackIntake(IntakeSubsystem intake) {
    this.intakeSubsystem = intake;
    addRequirements(intakeSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //System.out.println("init");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intakeSubsystem.runBackIntake(IntakeConstants.INTAKE_SPEED);
    //System.out.println("execute");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeSubsystem.stopBackIntake();
    //System.out.println("stop");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //System.out.println("isFinished");
    return false;
  }
}
