package Logica.crud.commands;

import javax.mail.*;
import javax.mail.internet.*;

import Logica.crud.dto.CitaDto;
import Logica.crud.dto.PacienteDto;

import java.util.*;
public class EnviarGmailUrgente 
{
	 private final Properties properties = new Properties();
	 private Session session;
	 private String correoMedico;
	 private PacienteDto paciente;
	 private CitaDto cita;
	 public EnviarGmailUrgente(String correo,PacienteDto pa,CitaDto ci) 
	 {
		    properties.put("mail.smtp.host", "smtp-mail.outlook.com");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.port","587");;
	        properties.put("mail.smtp.auth", "true");
	 
	        session = Session.getDefaultInstance(properties);
	        this.correoMedico=correo;
	        this.paciente=pa;
	        this.cita=ci;
	 }
	 
	 public void execute(){
		 
	        try{
	            String correoEnvia = "HospitalIPS@outlook.es"; //Correo del emisor
	            String contrasena = "IPSFRPhospital1"; //Contrase�a del emisor
	            String des = correoMedico; //Correo del destinatario
	            String asunto = "Urgencia Hospital Central";
	            String mensaje = "Se le solicita en el Hospital para atender una Urgencia: /t Paciente:"+this.paciente.name+
	            		" "+this.paciente.surname+" con dni "+this.paciente.dni+" /t Cita:"+this.cita.id+" Hora inicio:"+this.cita.horaInicio
	            		+" Hora final:"+this.cita.horaFinal;
	            
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(correoEnvia));
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(des));
	            //message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
	            message.setSubject(asunto);
	            message.setText(mensaje);
	            
	            
	            Transport t = session.getTransport("smtp");
	            t.connect(correoEnvia, contrasena);
	            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
	            t.close();
	        }catch (MessagingException me){
	                        //Aqui se deberia o mostrar un mensaje de error o en lugar
	                        //de no hacer nada con la excepcion, lanzarla para que el modulo
	                        //superior la capture y avise al usuario con un popup, por ejemplo.
	            System.err.print(me);;
	            return;
	        }
	        
	    }
 
}
