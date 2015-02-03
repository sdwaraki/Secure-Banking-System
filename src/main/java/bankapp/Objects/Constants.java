package bankapp.Objects;

import java.util.HashMap;
import java.util.Map;

public class Constants 
{
	
	public static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	/* Types of Employee Roles */
	public static final String EMPLOYEE_TYPE="EMPLOYEE";
	public static final String CUSTOMER_TYPE="CUSTOMER";
	public static final String IT_MANAGER="IT_MANAGER";
	public static final String IT_EMPLOYEE="IT_EMPLOYEE";
	public static final String ADMIN="ADMIN";
	public static final String SALES_MANAGER="SALES_MANAGER";
	public static final String SALES_EMPLOYEE="SALES_EMPLOYEE";
	public static final String TRANSACTION_MANAGER="TRANSACTION_MANAGER";
	public static final String TRANSACTION_EMPLOYEE="TRANSACTION_EMPLOYEE";
	public static final String HR_MANAGER="HR_MANAGER";
	public static final String HR_EMPLOYEE="HR_EMPLOYEE";
	public static final String CORPORATE_EMPLOYEE="CORPORATE_EMPLOYEE";
	
	
	/* Types of Customer Roles */
	public static final String INDIVIDUAL_CUSTOMER="INDIVIDUAL_CUSTOMER";
	public static final String MERCHANT_CUSTOMER="MERCHANT_CUSTOMER";
	/* Request Status */
	public static final String PENDING="PENDING";
	public static final String COMPLETE="COMPLETE";
	public static final String APPROVED="APPROVED";
	public static final String DENIED="DENIED";
	/*Request Types*/
	public static final String CREATE_EMPLOYEE_REQUEST="CREATE_EMPLOYEE";
	public static final String DELETE_EMPLOYEE_REQUEST="DELETE_EMPLOYEE";
	public static final String CREATE_TROUBLESHOOT_REQUEST="CREATE_TROUBLESHOOT";
	public static final String ADD_CUSTOMER_REQUEST="CREATE_CUSTOMER";
	public static final String DELETE_CUSTOMER_REQUEST="DELETE_CUSTOMER";
	public static final String APPROVE_TRANSACTION="APPROVE_TRANSACTION";

	public static final String ADD_PAYEE_REQUEST="ADD_PAYEE";
	

	/* Audit */
	public static final String ADD_RECEIPENT = "ADD_RECEIPENT";
	public static final String ADD_ACCOUNT = "ADD_ACCOUNT";
	public static final String ADD_CUSTOMER_AND_ACCOUNTS="ADD_CUSTOMER_AND_ACCOUNTS";
	public static final String ADD_CUST_REQUEST="ADD_CUSTOMER_REQUEST";
	public static final String DELETE_CUST_REQUEST="DELETE_CUSTOMER_REQUEST";
	public static final String ADD_EMP_REQUEST="ADD_EMPLOYEE_REQUEST";
	public static final String DEL_EMP_REQUEST="DELETE_EMPLOYEE_REQUEST";
	public static final String TRANSFER_EMPLOYEE="TRANFER_EMPLOYEE";

	/* States for users */
	public static final Integer ACTIVE=1;
	public static final Integer INACTIVE=0;
	
	/*Account Type*/
	public static final String CHECKINGS="CHECKINGS";
	public static final String SAVINGS="SAVINGS";
	
	/*TRANSACTION TYPE*/
	public static final String CREDIT="CREDIT";
	public static final String DEBIT="DEBIT";
	
	public static final Map<String, String> pageMap;

	
    static
    {
        pageMap = new HashMap<String, String>();
        pageMap.put(IT_MANAGER, "/home/ItManagerHomePage");
        pageMap.put(IT_EMPLOYEE, "/home/ItEmployeeHomePage");
        pageMap.put(SALES_MANAGER, "/home/SalesManagerHomePage");
        pageMap.put(SALES_EMPLOYEE, "/home/SalesEmployeeHomePage");
        pageMap.put(TRANSACTION_MANAGER, "/home/TransactionManagerHomePage");
        pageMap.put(TRANSACTION_EMPLOYEE, "/home/TransactionEmployeeHomePage");
        pageMap.put(HR_MANAGER, "/home/HrManagerHomePage");
        pageMap.put(HR_EMPLOYEE, "/home/HrEmployeeHomePage");
        pageMap.put(ADMIN, "/home/AdminHomePage");
        pageMap.put(INDIVIDUAL_CUSTOMER, "/home/IndividualCustomerHomePage");
        pageMap.put(MERCHANT_CUSTOMER, "/home/MerchantCustomerHomePage");
        
    }

}
