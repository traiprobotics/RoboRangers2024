// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.PIDConstants;
import frc.robot.Constants.PitchConstants;
import frc.robot.Constants.TurretConstants;
import frc.robot.Constants.YawConstants;
import frc.robot.commands.DriveArcade;
import frc.robot.commands.RunFrontIntake;
import frc.robot.commands.SprintDriveArcade;
import frc.robot.commands.automatic.CameraShooterPitch;
import frc.robot.commands.automatic.CameraTurretYaw;
import frc.robot.commands.automatic.FrontIntakeAndIndex;

import frc.robot.commands.automatic.BackIntakeAndIndex;
import frc.robot.commands.turret.SetShooterPitchPreset;
import frc.robot.commands.turret.SetTurretYaw;
import frc.robot.commands.turret.SetTurretYawPreset;
import frc.robot.commands.turret.RunIndexer;
import frc.robot.commands.turret.RunIndexerAndShooter;
import frc.robot.commands.turret.RunShooter;
import frc.robot.commands.turret.SetShooterPitch;

import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.turret.IndexerSubsystem;
import frc.robot.subsystems.turret.PhotonLimelightSubsystem;
import frc.robot.subsystems.turret.ShooterPitchSubsystem;
import frc.robot.subsystems.turret.ShooterSubsystem;
import frc.robot.subsystems.turret.TurretYawSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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
  private static final TurretYawSubsystem m_turretYaw = new TurretYawSubsystem();
  private static final ShooterSubsystem m_shooter = new ShooterSubsystem();
  private static final IndexerSubsystem m_indexer = new IndexerSubsystem();
  private static final PhotonLimelightSubsystem m_limelight = new PhotonLimelightSubsystem();

  private static DigitalInput m_indexerLimit = new DigitalInput(2);

  private Joystick driveJoystick = new Joystick(0);
  private CommandXboxController controlController = new CommandXboxController(1);
  
  //make auto dropdown variables
  private final SendableChooser<String> m_autoChooser = new SendableChooser<>();
  private final String auto1 = "1";
  private final String auto2 = "2";
  private final String auto3 = "3";

  //make team dropdown variables
  // private final SendableChooser<String> m_teamTagChooser = new SendableChooser<>();
  // private final String red = "1";
  // private final String blue = "2";

  public int trackedSpeakerTag = 4;

  //initialize auto commands

  // private static final ShootAndBackAuto shootAndBackAuto = new ShootAndBackAuto(m_shooter, m_drivetrain);

  
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    defaultCommands();

    //add auto options to dropdown
    m_autoChooser.setDefaultOption("Back Up", auto1);
    m_autoChooser.addOption("Shoot Straight and Back Up", auto2);
    m_autoChooser.addOption("Shoot, Back Up, and Intake Note", auto2);

    //add team options to dropdown
    // m_teamTagChooser.setDefaultOption("Red Alliance", red);
    // m_teamTagChooser.addOption("Blue Alliance", blue);

    //push options to dashboard
    SmartDashboard.putData("Rat Sh*t Auto Selection", m_autoChooser);
    SmartDashboard.putNumber("Auto Wait Time (seconds)", 0);
    //(idea from overdrive lol, for if like multiple people have shoot autos)

    SmartDashboard.putNumber("Pitch P Gain", PIDConstants.PITCH_P);
    SmartDashboard.putNumber("Pitch I Gain", PIDConstants.PITCH_I);
    SmartDashboard.putNumber("Pitch D Gain", PIDConstants.PITCH_D);
    SmartDashboard.putNumber("Pitch FF", PIDConstants.PITCH_FF);
    SmartDashboard.putNumber("Pitch Min Output", PIDConstants.PITCH_POWER_MIN);
    SmartDashboard.putNumber("Pitch Max Output", PIDConstants.PITCH_POWER_MAX);

  }

  private void defaultCommands() {
    //m_drivetrain.setDefaultCommand(new DriveArcade(m_drivetrain, driveJoystick));
    //m_shooterPitch.setDefaultCommand(new GetShooterPitchEncoder(m_shooterPitch));
    m_shooterPitch.setDefaultCommand(new SetShooterPitch(m_shooterPitch, controlController));
    m_turretYaw.setDefaultCommand(new SetTurretYaw(m_turretYaw, controlController));
  }

  public void teleopDefaultCommands() {
    m_drivetrain.setDefaultCommand(new DriveArcade(m_drivetrain, driveJoystick));
  }

  public void teleopInitCommands() {
    m_shooterPitch.updateDashboard();
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

    controlController.leftBumper().whileTrue((new BackIntakeAndIndex(m_intake, m_indexer, m_turretYaw, m_shooterPitch, m_indexerLimit)));
    controlController.rightBumper().whileTrue((new FrontIntakeAndIndex(m_intake, m_indexer, m_turretYaw, m_shooterPitch, m_indexerLimit)));

    controlController.pov(270).whileTrue(new SetTurretYawPreset(m_turretYaw, YawConstants.RIGHT_SIDE));
    controlController.pov(90).whileTrue(new SetTurretYawPreset(m_turretYaw, YawConstants.LEFT_SIDE));

    controlController.button(7).whileTrue(
      new ParallelDeadlineGroup(
        new CameraTurretYaw(m_turretYaw, m_limelight, trackedSpeakerTag), 
        new CameraShooterPitch(m_shooterPitch, m_limelight, trackedSpeakerTag)
        //new RunShooter(m_shooter)
      )
    );

    controlController.y().whileTrue(new RunShooter(m_shooter));

    controlController.x().whileTrue(new RunIndexer(m_indexer, TurretConstants.INDEXER_SHOOT_SPEED));
    controlController.a().whileTrue(new RunIndexer(m_indexer, TurretConstants.INDEXER_NORMAL_SPEED));
    controlController.b().whileTrue(new RunIndexerAndShooter(m_indexer, m_shooter, TurretConstants.INDEXER_BACK_SPEED, TurretConstants.SHOOTER_BACK_SPEED));
   
    Trigger sprint = new JoystickButton(driveJoystick, 1);
    sprint.whileTrue(new SprintDriveArcade(m_drivetrain, driveJoystick));

    controlController.pov(0).whileTrue(new SetShooterPitchPreset(m_shooterPitch, PitchConstants.AMP_SCORE_PITCH));

    //semi-auto commands
    //(new BackIntakeAndIndex(m_intake, m_indexer, m_turretYaw, m_shooterPitch));
    //(new FrontIntakeAndIndex(m_intake, m_indexer, m_turretYaw, m_shooterPitch));
  }

  // public void autoSetDriveSpeed(double leftSpeed, double rightSpeed) {
  //   m_drivetrain.setDriveSpeed(leftSpeed, rightSpeed);
  // }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    Command auto;
    
    auto = new SequentialCommandGroup(
      new RunFrontIntake(m_intake, 1)
    );
    
    // get auto to run from sendableChooser dropdown
    // switch (m_autoChooser.getSelected()) {
    //   default:
    //   case auto1:
    //     auto = new AutoDrive(m_drivetrain, 8, 0);
    //     break;
    //   case auto2:
    //     auto = new SequentialCommandGroup(
    //       new AutoShoot(m_shooter, m_indexer),
    //       new AutoDrive(m_drivetrain, 8, 0)
    //     );
    //     break;
    //   case auto3:
    //     auto = null;
    // }

    return auto;
  }
}
