// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.StadiaController.Button;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.DriveArcade;
import frc.robot.commands.RunBackIntake;
import frc.robot.commands.RunClimbLeft;
import frc.robot.commands.RunClimbRight;
import frc.robot.commands.RunFrontIntake;
import frc.robot.commands.turret.GetShooterPitchEncoder;
import frc.robot.commands.turret.RunIndexerNormal;
import frc.robot.commands.turret.RunIndexerShoot;
import frc.robot.commands.turret.RunShooter;
import frc.robot.commands.turret.driveShooterPitch;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LeftClimbSubsystem;
import frc.robot.subsystems.RightClimbSubsystem;
import frc.robot.subsystems.turret.IndexerSubsystem;
import frc.robot.subsystems.turret.ShooterPitchSubsystem;
import frc.robot.subsystems.turret.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private static final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
  private static final IntakeSubsystem m_intake = new IntakeSubsystem();
  private static final ShooterPitchSubsystem m_shooterPitch = new ShooterPitchSubsystem();
  private static final ShooterSubsystem m_shooter = new ShooterSubsystem();
  private static final IndexerSubsystem m_indexer = new IndexerSubsystem();
  private static final LeftClimbSubsystem m_leftClimb = new LeftClimbSubsystem();
  private static final RightClimbSubsystem m_rightClimb = new RightClimbSubsystem();

  private Joystick driveJoystick = new Joystick(0);
  private CommandXboxController controlController = new CommandXboxController(1);
  //Trigger rightBumper = controlController.rightBumper();



  // Replace with CommandPS4Controller, CommandJoystick, CommandXboxController if needed
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    defaultCommands();
    
  }

  private void defaultCommands() {
    m_drivetrain.setDefaultCommand(new DriveArcade(m_drivetrain, driveJoystick));
    m_shooterPitch.setDefaultCommand(new GetShooterPitchEncoder(m_shooterPitch));
    m_shooterPitch.setDefaultCommand(new driveShooterPitch(m_shooterPitch, controlController));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_exampleSubsystem::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem));

    // // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // // cancelling on release.
    // m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

    controlController.rightBumper().whileTrue(new RunFrontIntake(m_intake));
    controlController.leftBumper().whileTrue(new RunBackIntake(m_intake));
    controlController.y().whileTrue(new RunShooter(m_shooter));
    controlController.x().whileTrue(new RunIndexerShoot(m_indexer));
    controlController.a().whileTrue(new RunIndexerNormal(m_indexer));
   
    Trigger button11 = new JoystickButton(driveJoystick, 11);
    button11.whileTrue(new RunClimbLeft(m_leftClimb));

    Trigger button12 = new JoystickButton(driveJoystick, 12);
    button12.whileTrue(new RunClimbRight(m_rightClimb));



    //.whileTrue(new RunClimbLeft(m_leftClimb));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An example command will be run in autonomous
  //   return Autos.exampleAuto(m_exampleSubsystem);
  // }
}
