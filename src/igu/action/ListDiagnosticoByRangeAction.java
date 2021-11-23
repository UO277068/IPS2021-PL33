package igu.action;

import java.util.List;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.DiagnosticoDto;

public class ListDiagnosticoByRangeAction {
	 private CitaCrudService service = BusinessFactory.forCitaCrudService();
	    
	    private String cap;
		
		public ListDiagnosticoByRangeAction(String cap) 
		{
	      this.cap=cap;
		}

		public List<DiagnosticoDto> execute(){

			List<DiagnosticoDto> citas =null;
			try {
				citas = service.listDiagnosticoByRange(cap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return citas;
			
		}
}
