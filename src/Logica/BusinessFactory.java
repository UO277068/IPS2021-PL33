package Logica;

import Logica.crud.CitaCrudService;
import Logica.crud.CitaCrudServiceImpl;

public class BusinessFactory {



	public static CitaCrudService forCitaCrudService() {
		return new CitaCrudServiceImpl();
	}

//	public static InvoicingService forInvoicingService() {
//		return new InvoicingServiceImpl();
//	}
//	
//	public static PaymentmeanCrudService forPaymentMeanCrudService() {
//		return new PaymentmeanCrudServiceImpl();
//	}
//	
//	public static VoucherService forVoucherService() {
//		return new VoucherServiceImpl();
//	}
//
//	public static ClientCrudService forClientCrudService() {
//		return new ClientCrudServiceImpl();
//	}

}

