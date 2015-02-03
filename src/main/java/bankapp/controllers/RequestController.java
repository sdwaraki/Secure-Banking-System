package bankapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bankapp.Objects.ApplicationContextUtils;
import bankapp.Objects.BankUser;
import bankapp.Objects.Constants;
import bankapp.Objects.MyRequestsBackBean;
import bankapp.Objects.Request;
import bankapp.Objects.Role;

@Controller
public class RequestController 
{
	private enum roleNames{
		IT_MANAGER,IT_EMPLOYEE,SALES_MANAGER,SALES_EMPLOYEE,HR_MANAGER,HR_EMPLOYEE,TRANSACTION_MANAGER,TRANSACTION_EMPLOYEE,ADMIN,INDIVIDUAL_CUSTOMER,MERCHANT_CUSTOMER
	}
	/* @RequestMapping(value = "/request/GetPendingRequests", method = RequestMethod.GET)
	 public ModelAndView getPendingRequests(Model model) 
	 {
		 List<Request> requests= new ArrayList<Request>();
		 requests=ApplicationContextUtils.getRequestJDBCTemplate().getPendingRequestBy(ApplicationContextUtils.getLoggedInUser().getUserId());
		 model.addAttribute("requests",requests);
	     return new ModelAndView("/request/GetPendingRequests", "Request",  requests);	 
	 }*/
	/* @RequestMapping(value = "/request/AuthorizeRequests", method = RequestMethod.GET)
	 public ModelAndView authorizeRequests(Model model) 
	 {
		 List<Request> requests= new ArrayList<Request>();
		 requests=ApplicationContextUtils.getRequestJDBCTemplate().getPendingRequestFrom(ApplicationContextUtils.getLoggedInUser().getUserId());
		 model.addAttribute("requests",requests);
	     return new ModelAndView("/request/AuthorizeRequests", "Request",  requests);	 
	 }*/
	 @RequestMapping(value = "/request/ApproveRequest", method = RequestMethod.GET)
	 public ModelAndView approveRequests(HttpServletRequest request,HttpServletResponse response) 
	 {
		 String requestId= request.getParameter("id");
		 ApplicationContextUtils.getRequestJDBCTemplate().changeStatus(requestId, Constants.APPROVED);
		 return new ModelAndView("results","Request",new Request());
		
	 }
	
	 @RequestMapping(value = "/request/CreateRequest", method = RequestMethod.GET)
	 public ModelAndView createRequestToAdmin(String requestType) 
	 {
		 Request r=new Request();
		 r.setRequestType(requestType);
		 r.setRequestFrom(ApplicationContextUtils.getLoggedInUser().getUserId());
		 ApplicationContextUtils.getRequestJDBCTemplate().updateRequestToAdmin(r);
		 return new ModelAndView("results","Request",new Request());
		
	 }
		
		@RequestMapping(value = "/request/TroubleShoot", method = RequestMethod.GET)
		public String gotoHome8(Model model) {
			model.addAttribute("Msg","");
			return "/request/RaiseTroubleshootRequest";	
		
		}
		
		@RequestMapping(value = "/request/AuthorizeRequests", method = RequestMethod.GET)
		public ModelAndView AuthorizeRequestsGet(Model model) 
		{
			List<Request> requests= new ArrayList<Request>();
			
			if(ApplicationContextUtils.getLoggedInUser().getRoles().get(0).getName().equals(Constants.ADMIN))
			{
				requests= ApplicationContextUtils.getRequestJDBCTemplate().getAllApprovedRequestTo(ApplicationContextUtils.getLoggedInUser().getUserId());
			}
			else
				requests= ApplicationContextUtils.getRequestJDBCTemplate().getPendingRequestBy(ApplicationContextUtils.getLoggedInUser().getUserId());
		
			for(Request r:requests)
			{
				BankUser b=ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserId(r.getRequestFrom());
				r.setRequestFrom(b.getFirstName()+" "+b.getLastName());
			}
			List<String> requestStatus = populateStatus();
			MyRequestsBackBean m= new MyRequestsBackBean();
			m.setMyRequests(requests);
			System.out.println("---------------------------"+m.getMyRequests().size());
			model.addAttribute("requestStatus",requestStatus);
			model.addAttribute("myRequests",m.getMyRequests());
			model.addAttribute("Msg","");
			return new ModelAndView("/request/AuthorizeRequests","MyRequestsBackBean",m);
		
		}
		@RequestMapping(value = "/request/GetPendingRequests", method = RequestMethod.GET)
		public ModelAndView GetPendingRequestsGet(Model model) 
		{
			List<Request> requests= new ArrayList<Request>();
			requests= ApplicationContextUtils.getRequestJDBCTemplate().getAllRequestByFrom(ApplicationContextUtils.getLoggedInUser().getUserId());
			List<String> requestStatus = populateReadOnlyStatus();
			MyRequestsBackBean m1= new MyRequestsBackBean();
			for(Request r:requests)
			{
				BankUser b=ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserId(r.getRequestFrom());
				r.setRequestFrom(b.getFirstName()+" "+b.getLastName());
			}
			m1.setMyRequests(requests);
			System.out.println(m1.getMyRequests().size());
			model.addAttribute("requestStatus",requestStatus);
			model.addAttribute("myRequests",m1.getMyRequests());
			model.addAttribute("Msg","Succesfully Updated");
			return new ModelAndView("/request/GetPendingRequests","MyRequestsBackBean",m1);		
		}
		@RequestMapping(value = "/request/viewMyEmployeeRequest", method = RequestMethod.GET)
		public ModelAndView gotoHome10(Model model) 
		{
			List<Request> requests= new ArrayList<Request>();
			requests= ApplicationContextUtils.getRequestJDBCTemplate().getAllMyEmployeeRequest();
			List<String> requestStatus = populateReadOnlyStatus();
			MyRequestsBackBean m1= new MyRequestsBackBean();
			for(Request r:requests)
			{
				BankUser b=ApplicationContextUtils.getBankUserJDBCTemplate().getUserByUserId(r.getRequestFrom());
				r.setRequestFrom(b.getFirstName()+" "+b.getLastName());
			}
			m1.setMyRequests(requests);
			System.out.println(m1.getMyRequests().size());
			model.addAttribute("requestStatus",requestStatus);
			model.addAttribute("myRequests",m1.getMyRequests());
			model.addAttribute("Msg","");
			return new ModelAndView("/request/GetPendingRequests","MyRequestsBackBean",m1);
		
		}
		private List<String> populateReadOnlyStatus() 
		{
			List<String> requestStatus= new ArrayList<String>();
			requestStatus.add(Constants.APPROVED);
			requestStatus.add(Constants.DENIED);
			requestStatus.add(Constants.PENDING);
			requestStatus.add(Constants.COMPLETE);
			return requestStatus;
		}
		private List<String> populateStatus() 
		{
			String roleName= ApplicationContextUtils.getLoggedInUser().getRoles().get(0).getName();
			List<String> requestStatus= new ArrayList<String>();
			roleNames r= roleNames.valueOf(roleName);
			switch(r)
			{
				case  IT_EMPLOYEE:		
										requestStatus.add(Constants.PENDING);
										requestStatus.add(Constants.COMPLETE);
									break;
				case IT_MANAGER:
								
									break;
				case HR_EMPLOYEE :
									break;
									
				case HR_MANAGER:			
											requestStatus.add(Constants.PENDING);
											requestStatus.add(Constants.APPROVED);
									break;
										
				case ADMIN :				
											requestStatus.add(Constants.PENDING);
											requestStatus.add(Constants.APPROVED);
											requestStatus.add(Constants.COMPLETE);
									break;
				case SALES_EMPLOYEE:
					
									break;
								
				case SALES_MANAGER:			
											requestStatus.add(Constants.APPROVED);
											requestStatus.add(Constants.PENDING);
											requestStatus.add(Constants.DENIED);
									break;
								
				case TRANSACTION_EMPLOYEE:
											requestStatus.add(Constants.APPROVED);
											requestStatus.add(Constants.DENIED);
											requestStatus.add(Constants.PENDING);
									break;
				case TRANSACTION_MANAGER:
											requestStatus.add(Constants.APPROVED);
											requestStatus.add(Constants.DENIED);
											requestStatus.add(Constants.PENDING);
										break;
							
				case INDIVIDUAL_CUSTOMER:
											requestStatus.add(Constants.DENIED);
											requestStatus.add(Constants.PENDING);
											requestStatus.add(Constants.COMPLETE);
										break;						
				case MERCHANT_CUSTOMER:
											requestStatus.add(Constants.DENIED);
											requestStatus.add(Constants.PENDING);
											requestStatus.add(Constants.COMPLETE);
											break;
			}
			
			return requestStatus;
		}
		
		@RequestMapping(value = "/request/AuthorizeRequests", method = RequestMethod.POST)
		public ModelAndView AuthorizeRequestsPost(@ModelAttribute MyRequestsBackBean m,Model model) 
		{
			
			for(Request r:m.getMyRequests())
			{
				ApplicationContextUtils.getRequestJDBCTemplate().changeStatus(r.getRequestID(), r.getRequestStatus());
			}
			model.addAttribute("Msg", "Update Sucess!");
			return new ModelAndView("results","MyRequestsBackBean",m);
		}
	 @RequestMapping(value = "/request/processTroubleShootRequest", method = RequestMethod.POST)
	 public String processTroubleShootRequest(HttpServletRequest request,HttpServletResponse response,Model model) 
	 {
		 String Subject="REQ SUBJECT:-"+request.getParameter("subject");
		 String Description="REQ DESCRIPTION:-"+request.getParameter("description");
		 Request r= new Request();
		 r.setRequest(Subject+"\n\n"+Description);
		 ApplicationContextUtils.getRequestJDBCTemplate().createTroubleShootRequest(r);
		 model.addAttribute("Msg","Sucessfully Submitted");
		 return "results";	
	 }

}
