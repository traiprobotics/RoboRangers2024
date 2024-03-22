// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.ClimbConstants;
import frc.robot.Constants.PitchConstants;
import frc.robot.Constants.TurretConstants;

import frc.robot.commands.Autos;
import frc.robot.commands.DriveArcade;
import frc.robot.commands.RunBackIntake;
import frc.robot.commands.RunClimbLeft;
import frc.robot.commands.RunClimbRight;
import frc.robot.commands.RunFrontIntake;
import frc.robot.commands.turret.SetShooterPitchPreset;
import frc.robot.commands.turret.GetShooterPitchEncoder;
import frc.robot.commands.turret.RunIndexer;
import frc.robot.commands.turret.RunShooter;
import frc.robot.commands.turret.SetShooterPitch;

import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LeftClimbSubsystem;
import frc.robot.subsystems.RightClimbSubsystem;
import frc.robot.subsystems.turret.IndexerSubsystem;
import frc.robot.subsystems.turret.ShooterPitchSubsystem;
import frc.robot.subsystems.turret.ShooterSubsystem;

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
    m_shooterPitch.setDefaultCommand(new SetShooterPitch(m_shooterPitch, controlController));
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

    controlController.x().whileTrue(new RunIndexer(m_indexer, TurretConstants.INDEXER_SHOOT_SPEED));
    controlController.a().whileTrue(new RunIndexer(m_indexer, TurretConstants.INDEXER_NORMAL_SPEED));
    controlController.b().whileTrue(new RunIndexer(m_indexer, TurretConstants.INDEXER_BACK_SPEED));
   
    //raise climb arms
    Trigger button9 = new JoystickButton(driveJoystick, 9);
    button9.whileTrue(new RunClimbLeft(m_leftClimb, -ClimbConstants.CLIMB_SPEED_UP));

    Trigger button10 = new JoystickButton(driveJoystick, 10);
    button10.whileTrue(new RunClimbRight(m_rightClimb, -ClimbConstants.CLIMB_SPEED_UP));

    //lower climb arms
    Trigger button11 = new JoystickButton(driveJoystick, 11);
    button11.whileTrue(new RunClimbLeft(m_leftClimb, ClimbConstants.CLIMB_SPEED_DOWN));

    Trigger button12 = new JoystickButton(driveJoystick, 12);
    button12.whileTrue(new RunClimbRight(m_rightClimb, ClimbConstants.CLIMB_SPEED_DOWN));

    controlController.pov(0).whileTrue(new SetShooterPitchPreset(m_shooterPitch, PitchConstants.AMP_SCORE_PITCH));
    

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
