// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.malfunctionz.malfunctionboard.nt.MalfunctionBoard;
import com.malfunctionz.malfunctionboard.nt.datatypes.*;

@SuppressWarnings("removal")
public class Robot extends TimedRobot {
  
  private XboxController controller;
  private final MalfunctionBoard dashboard = new MalfunctionBoard();
  public static SparkMaxConfig DefaultConfig = new SparkMaxConfig();    
  private SparkMax rightMotor;
  private SparkMax leftMotor;
  public static final int leftMotorCanID = 7;
  public static final int rightMotorCanID = 2;

  static {
    DefaultConfig.smartCurrentLimit(50);
    DefaultConfig.idleMode(IdleMode.kCoast);
    DefaultConfig.openLoopRampRate(1.0);
    DefaultConfig.inverted(false);
  }

  public Robot() {}

  @Override
  public void robotInit() {
    leftMotor = new SparkMax(leftMotorCanID, MotorType.kBrushless);
    leftMotor.configure(DefaultConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
    rightMotor = new SparkMax(rightMotorCanID, MotorType.kBrushless);
    rightMotor.configure(DefaultConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
    controller = new XboxController(0);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    if (Math.abs(controller.getLeftY()) > 0.1) {
      leftMotor.set(-controller.getLeftY());
    }
    else {
      leftMotor.stopMotor();
    }
    if (Math.abs(controller.getRightY()) > 0.1) {
      rightMotor.set(controller.getRightY());
    }
    else {
      rightMotor.stopMotor();
    }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}

