package com.eb.skeleton;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *		   This class is used to verify if the Code Skeleton is intact
 *         and not modified by participants thereby ensuring smooth auto
 *         evaluation
 */

public class SkeletonValidator {
	public SkeletonValidator() {
		validateClassName("com.eb.util.ElectricityBoard");
		validateClassName("com.eb.model.EBConnection");
		validateClassName("com.eb.exception.InvalidEBConnectionException");
		validateClassName("com.eb.test.ElectricityBoardTest");

		validateMethodSignature(
				"getConnectionType:java.lang.String,setConnectionType:void,setConnectionId:void,getOwnerName:java.lang.String,getPhase:java.lang.String,getMeterId:java.lang.String,setPhase:void,getConnectionId:int,setMeterId:void,setOwnerName:void",
				"com.eb.model.EBConnection");
		validateMethodSignature(
				"viewEBConnectionsByConnectionType:java.util.List,viewEBConnectionsConnectionTypeWise:java.util.Map,countTotalConnectionForEachPhase:java.util.Map,viewEBConnectionById:com.eb.model.EBConnection,getEbConnectionList:java.util.List,validateConnectionType:boolean,setEbConnectionList:void",
				"com.eb.util.ElectricityBoard");
		validateMethodSignature(
				"test11ValidateConnectionTypeWhenDomestic:void,test12ValidateConnectionTypeWhenCommercial:void,test13ValidateConnectionTypeWhenIndustrial:void,test14ValidateConnectionTypeWhenInvalid:void,test15ViewEBConnectionByIdWhenValid:void,test16ViewEBConnectionByIdWhenInvalid:void,test17ViewEBConnectionsByConnectionType:void,test18ViewEBConnectionsConnectionTypeWise:void,test19CountTotalConnectionForEachPhase:void,test20ViewEBConnectionsByConnectionTypeForEmptyList:void,test21ViewEBConnectionsConnectionTypeWiseForEmptyList:void,test22CountTotalConnectionForEachPhaseForEmptyList:void",
				"com.eb.test.ElectricityBoardTest");

	}

	private static final Logger LOG = Logger.getLogger("SkeletonValidator");

	protected final boolean validateClassName(String className) {

		boolean iscorrect = false;
		try {
			Class.forName(className);
			iscorrect = true;
			LOG.info("Class Name " + className + " is correct");

		} catch (ClassNotFoundException e) {
			LOG.log(Level.SEVERE, "You have changed either the " + "class name/package. Use the correct package "
					+ "and class name as provided in the skeleton - "+className);

		} catch (Exception e) {
			LOG.log(Level.SEVERE,
					"There is an error in validating the " + "Class Name. Please manually verify that the "
							+ "Class name is same as skeleton before uploading");
		}
		return iscorrect;
	}

	protected final void validateMethodSignature(String methodWithExcptn, String className) {
		Class cls = null;
		String methodName = null;
		try {

			String[] actualmethods = methodWithExcptn.split(",");
			boolean errorFlag = false;
			String[] methodSignature;
			
			String returnType = null;

			for (String singleMethod : actualmethods) {
				boolean foundMethod = false;
				methodSignature = singleMethod.split(":");

				methodName = methodSignature[0];
				returnType = methodSignature[1];
				cls = Class.forName(className);
				Method[] methods = cls.getMethods();
				for (Method findMethod : methods) {
					if (methodName.equals(findMethod.getName())) {
						foundMethod = true;
						if (!(findMethod.getReturnType().getName().equals(returnType))) {
							errorFlag = true;
							LOG.log(Level.SEVERE, " You have changed the " + "return type in '" + methodName
									+ "' method. Please stick to the " + "skeleton provided");

						} else {
							LOG.info("Method signature of " + methodName + " is valid");
						}

					}
				}
				if (!foundMethod) {
					errorFlag = true;
					LOG.log(Level.SEVERE, " Unable to find the given public method " + methodName
							+ ". Do not change the " + "given public method name. " + "Verify it with the skeleton");
				}

			}
			if (!errorFlag) {
				LOG.info("Method signature is valid");
			}

		} catch (Exception e) {
			LOG.log(Level.SEVERE,
					" There is an error in validating the " + "method structure. Please manually verify that the "
							+ "Method signature is same as the skeleton before uploading. Class Name : "+className+", method name : "+methodName);
		}
	}

}
