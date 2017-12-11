package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Cliente;
import dao.Transaccion;
import dao.TransaccionesDAO;

/**
 * Servlet implementation class DescargaTransaccionesServlet
 */
@WebServlet("/DescargaTransaccionesServlet")
public class DescargaTransaccionesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DescargaTransaccionesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Se descargarán las transacciones");
		try {
			
			Gson gson = new Gson();

			String c = ((Cliente) request.getSession().getAttribute("clientSession")).getDni();
			List<Transaccion> lTrans = TransaccionesDAO.listaTransacciones(c);
			String jsonString = gson.toJson(lTrans);
			System.out.println(jsonString);

			String filename = "transacciones.txt";
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment;filename=" + "transacciones.xml");

			File file = new File(filename);
			FileWriter wr = new FileWriter(file);
			wr.write(jsonString);
			wr.close();
			FileInputStream fileIn = new FileInputStream(file);
			OutputStream out = response.getOutputStream();

			byte[] outputByte = new byte[(int) file.length()];

			while (fileIn.read(outputByte, 0, (int) file.length()) != -1) {
				out.write(outputByte, 0, (int) file.length());
			}
			System.out.println("Se ha descargado el fichero de transacciones del cliente " + c);
		} catch (Exception e) {
			System.out.println("ha fallado el proceso de descarga");
		}
	}

}
