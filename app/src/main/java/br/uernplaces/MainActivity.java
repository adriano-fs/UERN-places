package br.uernplaces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Vector;

import org.osmdroid.ResourceProxy;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.MapTile;
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;


import br.exemploopenstreetmap.R;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity  extends Activity implements LocationListener  {
	public MapView osm;
	private MapController mc;
	private LocationManager locationManager;
	private String str[] ={"http://mt3.google.com/vt/lyrs=s"};
	public ArrayList<Ponto> al []= new ArrayList[3];
	public GeoPoint meuLocal ;
	Button bt_b,ml,legenda,limpar;
	EditText et;
	String []string = new String[4];
	String s;
	int nivel=1;
	boolean muda=false,limpou=false;
	
	Marker meul;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt_b = (Button) findViewById(R.id.button1);
		ml = (Button) findViewById(R.id.button2);
		legenda = (Button) findViewById(R.id.button3);
		limpar = (Button) findViewById(R.id.button4);
		
		et = (EditText) findViewById(R.id.editText1);
		/*
		InputStream is = null;
		try {
			is = new FileInputStream("arquivo.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
		*/
      
		for(int i=1;i<al.length;i++){
			al[i]= new ArrayList<Ponto>();
		
			
			/*	
			 try {
					s = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			 string = s.split(",");
			
			al[i].add(new Ponto(Double.parseDouble(string[0]), Double.parseDouble(string[1]), string[2], string[3], 0));
			*/
			
			
			
			//ROMARIO E JAEDSON
			al[i].add(new Ponto(-5.20678, -37.3156,"DCE","Diret�rio Central dos Estudantes",R.drawable.institucional));
			al[i].add(new Ponto(-5.20686, -37.31552,"RU","Restaurante Universit�rio",R.drawable.restaurante));
			al[i].add(new Ponto(-5.20683, -37.31551,"CC","Centro de Conviv�ncia",R.drawable.controconvivencia));
			al[i].add(new Ponto(-5.20694, -37.31539,"ARFOR","Secretaria Geral",R.drawable.comum));
			al[i].add(new Ponto(-5.20691, -37.31515,"Diretoria de Rela��es Internacionais e Interinstitucionais","",R.drawable.comum));
			al[i].add(new Ponto(-5.20691, -37.31513,"Comit� de �tica / Comit� de Experimenta��o Animal","",R.drawable.comum));
			al[i].add(new Ponto(-5.20692, -37.31508,"Xerox","",R.drawable.xerox));
			al[i].add(new Ponto(-5.20743, -37.31459,"Caixa Eletr�nico","",R.drawable.caixaeletronic));
			al[i].add(new Ponto(-5.20745, -37.31462,"DAE","Diretoria de Assist�ncia Estudantil",R.drawable.comum));
			al[i].add(new Ponto(-5.20743, -37.31453,"Biblioteca de Peri�dicos","",R.drawable.biblioteca48));
			al[i].add(new Ponto(-5.20741, -37.31451,"Sala de estudo Coletivo","",R.drawable.comum));
			al[i].add(new Ponto(-5.20739, -37.31436,"Biblioteca","",R.drawable.biblioteca48));
			al[i].add(new Ponto(-5.20736, -37.31462,"Sala de Coordena��o e Secretaria (Biblioteca)","",R.drawable.comum));
			al[i].add(new Ponto(-5.20733, -37.31459,"Sala de Sele��o de Aquisi��o (Biblioteca)","",R.drawable.comum));
			al[i].add(new Ponto(-5.20732, -37.31462,"Sala de Restaura��o","",R.drawable.comum));
			al[i].add(new Ponto(-5.20697, -37.31452,"DIRCA","Diretoria de Registro e Controle Acad�mico",R.drawable.comum));
			al[i].add(new Ponto(-5.20695, -37.31456,"DAIN","Diretoria de Apoio a Inclus�o",R.drawable.departamento));
			al[i].add(new Ponto(-5.20688, -37.31451,"Secretaria da PROEG","",R.drawable.comum));
			al[i].add(new Ponto(-5.20685, -37.31454,"Administra��o de Cursos de Gradua��o","",R.drawable.institucional));
			al[i].add(new Ponto(-5.20677, -37.31467,"PROEG","Pro-Reitoria de Ensino de Gradua��o",R.drawable.institucional));
			al[i].add(new Ponto(-5.20674, -37.3145,"Doc�ncia Universit�ria - Administra��o de Cursos de Gradua��o - PROEG","",R.drawable.ic_launcher));
			al[i].add(new Ponto(-5.20655, -37.31453,"Sub-prefeitura do Campus Central","",R.drawable.comum));
			al[i].add(new Ponto(-5.20652, -37.31452,"Diretoria de Pesquisa","",R.drawable.comum));
			al[i].add(new Ponto(-5.20654, -37.31458,"CONSEPE/CONSUNI","C�maras de Ensino de Gradua��o",R.drawable.comum));
			al[i].add(new Ponto(-5.20651, -37.31459,"Diretoria de Pos-Gradua��o","",R.drawable.comum));
			al[i].add(new Ponto(-5.20654, -37.31469,"PROEX","Pr�-Reitoria de Extens�o",R.drawable.comum));
			al[i].add(new Ponto(-5.20651, -37.31473,"DECA","Diretoria de Educa��o, Cultura e Artes",R.drawable.comum));
			al[i].add(new Ponto(-5.2069, -37.31345,"Departamento de M�sica","",R.drawable.departamento));
			al[i].add(new Ponto(-5.20689, -37.31356,"Laborat�rio de Inform�tica - M�sica","",R.drawable.labinfo));
			al[i].add(new Ponto(-5.20688, -37.31362,"Conservat�rio de M�sica","",R.drawable.comum));
			al[i].add(new Ponto(-5.20689, -37.31366,"GPPEM","Grupo de Pesquisa em M�sica",R.drawable.comum));
			al[i].add(new Ponto(-5.20687, -37.31373,"PROFLETRAS","Mestrado de Letras",R.drawable.comum));
			al[i].add(new Ponto(-5.20671, -37.31358,"Salas de Aula - M�sica","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.20653, -37.31343,"LT","Laborat�rio de Teclado",R.drawable.labciencia));
			al[i].add(new Ponto(-5.20652, -37.3135,"LPI","Laborat�rio de Pr�tica Instrumental",R.drawable.labciencia));
			al[i].add(new Ponto(-5.20632, -37.31369,"Salas de Aula - Ci�ncias Econ�micas","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.2061, -37.31365,"LABECO","Laborat�rio Integrado de An�lise Ambiental e Ecol�gico Aplicado",R.drawable.labciencia));
			al[i].add(new Ponto(-5.20607, -37.31378,"FACEM","Faculdade de Ci�ncias Econ�micas",R.drawable.comum));
			al[i].add(new Ponto(-5.20609, -37.31392,"Salas de Aula Administra��o/Gest�o Ambiental","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.20591, -37.3136,"Laborat�rio de Hotelaria","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.2059, -37.31367,"Sala de Projeto - Turismo","",R.drawable.comum));
			al[i].add(new Ponto(-5.20609, -37.31409,"NESAT","N�cleo de Estudos Socioambientais e Territoriais",R.drawable.comum));
			al[i].add(new Ponto(-5.20609, -37.31416,"GPGFC","Grupo de Pesquisa em Gest�o, Finan�as e Contabilidade",R.drawable.comum));
			al[i].add(new Ponto(-5.20609, -37.31422,"DGA","Departamento de Gest�o Ambiental",R.drawable.departamento));
			al[i].add(new Ponto(-5.20609, -37.31426,"DETUR","Departamento de Turismo",R.drawable.departamento));
			al[i].add(new Ponto(-5.20608, -37.31432,"LABEA","Laborat�rio de estudos e Pesquisas em Educa��o Ambiental",R.drawable.labciencia));
			al[i].add(new Ponto(-5.20608, -37.3144,"NUDET","N�cleo de Desenvolvimento Territorial",R.drawable.comum));
			al[i].add(new Ponto(-5.20606, -37.31452,"Laborat�rio de Inform�tica - FACEM","",R.drawable.labinfo));
			al[i].add(new Ponto(-5.20607, -37.31456,"DAD","Departamento de Administra��o",R.drawable.departamento));
			al[i].add(new Ponto(-5.20588, -37.31412,"Salas de Aulas - Jornalismo","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.20587, -37.3142,"Laborat�rio de Inform�tica - Comunica��o Social","",R.drawable.labinfo));
			al[i].add(new Ponto(-5.20587, -37.31428,"R�dio Univesit�ria","",R.drawable.comum));
			al[i].add(new Ponto(-5.20586, -37.31436,"DECOM","Departamento de Comunica��o Social",R.drawable.departamento));
			al[i].add(new Ponto(-5.2059, -37.31449,"Secretaria de Ci�ncias Econ�micas","",R.drawable.comum));
			al[i].add(new Ponto(-5.20586, -37.31459,"Salas de Aulas - Ci�ncias Cont�beis","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.20568, -37.31403,"Sala de Multim�dia da FAFIC","",R.drawable.comum));
			al[i].add(new Ponto(-5.20567, -37.31409,"Laborat�rio de �udio-Visual da FAFIC","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.20566, -37.31415,"Salas de Aulas - Radialismo","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.20567, -37.31421,"Salas de Aulas - Publicidade e Propaganda","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.20564, -37.31453,"Salas de Aulas - Ci�ncias Sociais","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.20562, -37.31473,"DEaD","Diretoria de Educa��o a Dist�ncia",R.drawable.comum));
			al[i].add(new Ponto(-5.20561, -37.3148,"PPGCISH","Programa de Pos-Gradua��o em Ci�ncias Sociais e Humanas",R.drawable.comum));
			al[i].add(new Ponto(-5.20526, -37.3145,"MNEMIS","Grupo de Pesquisa Mem�ria, Identidade e Ensino de H�storia",R.drawable.comum));
			al[i].add(new Ponto(-5.20525, -37.31455,"Secretaria do PPGCISH","",R.drawable.comum));
			al[i].add(new Ponto(-5.20524, -37.31459,"Sala de Reuni�es do PPGCISH","",R.drawable.comum));
			al[i].add(new Ponto(-5.20524, -37.31463,"GCOM","Grupo de Pesquisa em Comunica��o, Cultura e Sociedade",R.drawable.comum));
			al[i].add(new Ponto(-5.20523, -37.31467,"Grupo de Pesquisa Estado Seguran�a P�blica e Cidadania","",R.drawable.comum));
			al[i].add(new Ponto(-5.20523, -37.31473,"Grupo de Pesquisa Hist�ria do Nordeste: Sociedade e Cultura","",R.drawable.comum));
			al[i].add(new Ponto(-5.20523, -37.31477,"Sala PIBID - Geografia","",R.drawable.comum));
			al[i].add(new Ponto(-5.20522, -37.31481,"GECOM","Grupo de Pesquisa do Pensamento Complexo",R.drawable.comum));
				
			
			//GETULIO
			al[i].add(new Ponto(-5.205321, -37.317157, "PPGSS","Programa de P�s-gradua��o em Sa�de e Sociedade",R.drawable.comum));
			al[i].add(new Ponto(-5.205317, -37.317217, "GPELL","Grupo de Pesquisa Liguistica e Literatura",R.drawable.comum));
			al[i].add(new Ponto(-5.205312, -37.317308, "GET", "Grupo de Estudo em Tradu��o",R.drawable.comum));
			al[i].add(new Ponto(-5.205308, -37.317328, "Secretaria","",R.drawable.comum));
			al[i].add(new Ponto(-5.205311, -37.317359,"GEDUERN", "Grupo de Estudos de Discurso da UERN",R.drawable.comum));
			al[i].add(new Ponto(-5.205142, -37.317029,"Secretaria - FALA", "",R.drawable.comum));
			al[i].add(new Ponto(-5.205129, -37.317085, "Coordena��o e Secretaria de L�ngua Portuguesa � Dist�ncia", "",R.drawable.comum));
			al[i].add(new Ponto(-5.205131, -37.317102, "Departamento de Letras Estrangeiras","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205128, -37.317125,"Sala de aula","",R.drawable.comum));
			al[i].add(new Ponto(-5.205120, -37.317173, "Laborat�rio","",R.drawable.labinfo));
			al[i].add(new Ponto(-5.205112, -37.317211, "Departamento de Letras Vern�culas","",R.drawable.departamento));

			al[i].add(new Ponto(-5.204707, -37.317217,"Sala de Dan�a","",R.drawable.comum));
			al[i].add(new Ponto(-5.204301, -37.317086,"Piscina","",R.drawable.piscina));
			
			//GETULIO
			al[i].add(new Ponto(-5.205370, -37.316507, "LES", "Laborat�rio de Engenharia de Software",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205367, -37.316581, "PET CC","",R.drawable.comum));
			al[i].add(new Ponto(-5.205363, -37.316608, "P�s-gradua��o em F�sica","",R.drawable.comum));
			al[i].add(new Ponto(-5.205359, -37.316649, "GEPEES", "Grupo de estudo e pesquisa em estado, educa��o e sociedade",R.drawable.comum));
			al[i].add(new Ponto(-5.205355, -37.316676, "Forma��o e Profissionaliza��o de Professores","",R.drawable.comum));
			al[i].add(new Ponto(-5.205347, -37.316722, "Grupo de estudo e pesquisa  psicol�gica e educa��o inclusiva","",R.drawable.comum));
			al[i].add(new Ponto(-5.205171, -37.316473, "Mestrado em Servi�o Social","",R.drawable.comum));
			al[i].add(new Ponto(-5.205166, -37.316548, "Secretaria FASSO e Servi�o Social","",R.drawable.comum));
			al[i].add(new Ponto(-5.205160, -37.316578, "Departamento de Servi�o Social","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205153, -37.316605, "NEPT","",R.drawable.comum));
			al[i].add(new Ponto(-5.205149, -37.316636, "NEM","",R.drawable.comum));
			al[i].add(new Ponto(-5.205151, -37.316660, "Coordena��o de Estagi�rios","",R.drawable.comum));
			al[i].add(new Ponto(-5.205148, -37.316680, "Biblioteca Setorial","",R.drawable.biblioteca48));
			al[i].add(new Ponto(-5.205143, -37.316704, "Sala de Estudos","",R.drawable.comum));
			al[i].add(new Ponto(-5.205141, -37.316726, "Centro Acad�mico","",R.drawable.comum));
			al[i].add(new Ponto(-5.205142, -37.316764, "N�cleo de Estudo e A��es na �rea da Crian�a e do Adolescente","",R.drawable.comum));
			al[i].add(new Ponto(-5.204953, -37.316458, "Audit�rio FASSO","",R.drawable.auditorio48));
			al[i].add(new Ponto(-5.204936, -37.316606, "Sala de aula","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.204924, -37.316747, "Sala de aula","",R.drawable.salaaula));
			
			al[i].add(new Ponto(-5.204266, -37.316487, "Gin�sio","",R.drawable.comum));
			al[i].add(new Ponto(-5.204196, -37.316337, "LES 2", "Laborat�rio de Engenharia de Software",R.drawable.labciencia));
			
			//CAIO
			al[i].add(new Ponto(-5.204796, -37.316021,"LEM","Laborat�rio de Ensino de Matem�tica",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204800, -37.315961,"LEC","Laborat�rio de Ensino de Computa��o",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204810, -37.315910,"Sala de Video Confer�ncia","",R.drawable.comum));
			al[i].add(new Ponto(-5.204816, -37.315794,"Salas de Aula - Ci�ncia da Computa��o / Matem�tica","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.204868, -37.315615,"LORDI","Laborat�rio de Redes e Sistemas Distribu�dos",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204862, -37.315674,"LOIA","Laborat�rio de Otimiza��o e Intelig�ncia Artificial",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204881, -37.315520,"Sala de Multim�dia da FANAT","",R.drawable.comum));
			al[i].add(new Ponto(-5.204919, -37.315521,"Laborat�rio de Historia Oral e Imagem / N�cleo de Documenta��o e Pesquisa Hist�rica","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204908, -37.315663,"LABINFO","Laborat�rio de Inform�tica da FANAT",R.drawable.labinfo));
			al[i].add(new Ponto(-5.204851, -37.315393,"Salas de Aula - F�sica","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.204873, -37.315177,"Salas de Aula - Qu�mica","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.205232, -37.315734,"Laborat�rio de Qu�mica I","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205229, -37.315799,"Laborat�rio de Mec�nica I e II","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205222, -37.315863,"Laborat�rio de Biologia I","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205216, -37.315923,"Laborat�rio de Biologia II","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205211, -37.315979,"Laborat�rio de F�sica Moderna / �pticas e Ondas","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205206, -37.316043,"Laborat�rio de Qu�mica II","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205261, -37.315505,"Dire��o da FAFIC","",R.drawable.comum));
			al[i].add(new Ponto(-5.205267, -37.315452,"Secretaria e Departamento - Ci�ncias Socias e Pol�ticas","",R.drawable.comum));
			al[i].add(new Ponto(-5.205272, -37.315397,"Secretaria e Departamento - Hist�ria","",R.drawable.comum));
			al[i].add(new Ponto(-5.205278, -37.315289,"Audit�rio da FAFIC","",R.drawable.auditorio48));
			al[i].add(new Ponto(-5.205288, -37.315191,"Audit�rio Reitor Helder Heronildes da Silva","",R.drawable.auditorio48));
			al[i].add(new Ponto(-5.205021, -37.315726,"Secretaria e Dire��o da FANAT","",R.drawable.comum));
			al[i].add(new Ponto(-5.205020, -37.315760,"Sala do Professor Rommel Wladimir","",R.drawable.comum));
			al[i].add(new Ponto(-5.205021, -37.315790,"DI","Departamento de Inform�tica",R.drawable.departamento));
			al[i].add(new Ponto(-5.205020, -37.315821,"Departamento de Matem�tica","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205020, -37.315854,"Departamento de Ci�ncias Biologicas","",R.drawable.departamento));
			al[i].add(new Ponto(-5.204638, -37.315700,"Laborat�rio de Cultura de Tecido Vegetal","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204629, -37.315754,"Laborat�rio de Cromatografia","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204612, -37.315959,"Laborat�rio de Ci�ncias Biologicas","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205683, -37.315938,"Salas de Aula - Pedagogia","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.205590, -37.315588,"Laborat�rio de Geoprocessamento","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205638, -37.315597,"Laborat�rio de Arqueologia Homem Potiguar","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205625, -37.315729,"Centro Acad�mico - Pedagogia","",R.drawable.comum));
			al[i].add(new Ponto(-5.206066, -37.316803,"�nibus","Parada de Onibus",R.drawable.onibus));
			//CAIO
		
			
			al[i].add(new Ponto(-5.205511, -37.316034,"Salas de aula - Pedagogia","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.205513, -37.315925,"Biblioteca setorial da FE","",R.drawable.biblioteca48));
			al[i].add(new Ponto(-5.205522, -37.315855,"Sala Multimidia II - Pedagogia","",R.drawable.comum));
			al[i].add(new Ponto(-5.205527, -37.315788,"Secretaria da faculdade de educa��o e p�s-gradua��o","",R.drawable.comum));
			al[i].add(new Ponto(-5.205580, -37.315719,"PET de pedagogia","",R.drawable.comum));
			al[i].add(new Ponto(-5.205962, -37.315516,"Salas de aula - Direito","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.205976, -37.315410,"Secretaria de Direito","",R.drawable.comum));
			al[i].add(new Ponto(-5.205985, -37.315376,"Departamento de Direito","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205982, -37.315290,"Salas de aula - Direito","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.205759, -37.315485,"Salas de aula - Direito","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.205795, -37.315242,"Departamento de Ci�ncias Cont�beis","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205787, -37.315295,"Departamento de Ci�ncias Econ�micas","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205778, -37.315356,"Centro Acad�mico - Ci�ncias Econ�mimcas","",R.drawable.comum));
			al[i].add(new Ponto(-5.205546, -37.315470,"Sala de Cartografia","",R.drawable.comum));
			al[i].add(new Ponto(-5.205558, -37.315430,"Laborat�rio de Geografia f�sica","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205558, -37.315385,"Laborat�rio de Greografia humana","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205563, -37.315345,"Secretaria e departamento de Geografia","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205569, -37.315295,"Secretaria e departamento de Filosofia","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205577, -37.315224,"PET de Ci�ncias sociais e pol�ticas","",R.drawable.comum));
			al[i].add(new Ponto(-5.205942, -37.315819,"Secretaria e dire��o da FE","",R.drawable.comum));
			al[i].add(new Ponto(-5.205934, -37.315870,"Orienta��o Acad�mica - FE","",R.drawable.comum));
			al[i].add(new Ponto(-5.205928, -37.315923,"Departamento de Educa��o","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205925, -37.315971,"Coordena��o de Educa��o","",R.drawable.comum));
			al[i].add(new Ponto(-5.205923, -37.316013,"PIBID - UERN","",R.drawable.comum));
			al[i].add(new Ponto(-5.205917, -37.316081,"Sala multimidia I - Pedagogia","",R.drawable.comum));
			al[i].add(new Ponto(-5.205038, -37.315409,"Salas de aula - Biologia e Geografia","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.205067, -37.315197,"Salas de aula - Qu�mica e Hist�ria","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.205005, -37.315916,"Departamento de Qu�mica","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205002, -37.315966,"Departamento de F�sica","",R.drawable.departamento));
			al[i].add(new Ponto(-5.204491, -37.316179,"Audit�rio da PRODEP","",R.drawable.auditorio48));
			al[i].add(new Ponto(-5.204488, -37.316101,"Coordena��o e secretaria da PRODEP","",R.drawable.comum));
			al[i].add(new Ponto(-5.204404, -37.316188,"Laborat�rio de microbiologia","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204356, -37.316146,"LABSECO","Laborat�rio de sistem�tica e ecologia animal",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204280, -37.316186,"LABFU","Laborat�rio de biologia funcional",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204238, -37.316138,"LESV","Laborat�rio de ecologia e sistem�tica vegetal",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204191, -37.316161,"LAMOp","Laborat�rio de analises magneticas e opticas",R.drawable.labciencia));
			
		}
			
		al[0]= new ArrayList<Ponto>();
		al[0].add(new Ponto(-5.205720, -37.315977,"FE","Faculdade de Educa��o",R.drawable.comum));
		al[0].add(new Ponto(-5.205746, -37.315395,"FAD","Faculdade de Direito",R.drawable.comum));
		al[0].add(new Ponto(-5.205050, -37.317230,"FALA","Faculdade de Letras e Artes",R.drawable.comum));
		al[0].add(new Ponto(-5.205123, -37.316566,"FASSO","Faculdade de Servi�o Social",R.drawable.comum));
		al[0].add(new Ponto(-5.205004, -37.315891,"FANAT","Faculdade de Ciencias Exatas e Naturais",R.drawable.comum));
		al[0].add(new Ponto(-5.204729, -37.316550,"FAEF","Faculdade de Educacao Fisica",R.drawable.comum));
		al[0].add(new Ponto(-5.20607, -37.31378,"FACEM","Faculdade de Ci�ncias Econ�micas",R.drawable.comum));
		al[0].add(new Ponto(-5.205015, -37.315310,"FAFIC","Faculdade de Filosofia e Ci�ncias Sociais",R.drawable.comum));
		al[0].add(new Ponto(-5.206848, -37.315509,"CC","Centro de Conviv�ncia",R.drawable.comum));
		al[0].add(new Ponto(-5.206583, -37.314689,"Pr�-reitorias","",R.drawable.comum));
		al[0].add(new Ponto(-5.207435, -37.314459,"Biblioteca","",R.drawable.comum));
		
		
		
	
		File dir = new File("/sdcard/osmdroid/tiles/Google Maps/");
		if(!dir.exists())		
			copyFilesToSdCard();
		
		
		
		bt_b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String palavra = et.getText().toString().toLowerCase();
				Ponto p;
				osm.getOverlays().clear();
				int i,j;
				Toast toast = null ;
				boolean achou=false;
				for(i=0;i<al.length;i++){
					
					for(j=0;j<al[i].size();j++){
						p = al[i].get(j);
						
						if(p.getTitle().toLowerCase().indexOf(palavra)!=-1 ||
								p.getDesc().toLowerCase().indexOf(palavra)!=-1){
							achou=true;
							addMarker(new GeoPoint(p.getX(),p.getY()), p.getTitle(), p.getDesc(),p.getI());
						}	
					}			
				}
				
				if(!achou){
					toast = Toast.makeText(getApplicationContext(), "Pesquisa sem retorno", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
	
		
		
		limpar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(limpou==false){
					limpou=true;
					osm.getOverlays().clear();
					mc.animateTo(osm.getMapCenter());
				}
					
				else if(limpou==true){
					
					adicionar();
				}
					
				
		
			}
		});
		
		ml.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Toast toast;
						
				osm.getOverlays().remove(meul);
				if(meuLocal!=null){
					meul = addMarker(meuLocal,"Meu local","",R.drawable.usuario);
					mc.animateTo(meuLocal);
				}
					
				else{
					toast = Toast.makeText(getApplicationContext(), "Sem localizacao!!", Toast.LENGTH_SHORT);
					toast.show();
				}
				
		
			}
		});
	
		
		legenda.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent (getApplicationContext(),Legenda.class); 
				
				startActivity(i);
			
			}
		});
		
		
		osm = (MapView) findViewById(R.id.mapView);
		//osm.setTileSource(TileSourceFactory.MAPNIK);
		
		
		osm.setTileSource(new OnlineTileSourceBase("Google Maps",ResourceProxy.string.unknown,14,20,256,".png",str) {
			
			@Override
			public String getTileURLString(MapTile arg0) {
				
				return getBaseUrl() + "&x=" + arg0.getX() + "&y=" + arg0.getY() + "&z=" + arg0.getZoomLevel();
			
			}
		});
				
				
				
		osm.setUseDataConnection(false);
	
		osm.setBuiltInZoomControls(true);
		osm.setMultiTouchControls(true);
		
		
		mc = (MapController) osm.getController();
		mc.setZoom(16);
		
		GeoPoint center = new GeoPoint(-5.2046106,-37.3162137);
		mc.animateTo(center);
		Ponto p;
		
		adicionar();
		
		osm.setMapListener(new MapListener() {
			
			@Override
			public boolean onZoom(ZoomEvent arg0){
				int z = osm.getZoomLevel();
				
				//if(et.getText().toString()==null){
					if(nivel==1&&z>17){
						nivel=2;
						adicionar();
					}
					else if(nivel==2&&z<=17){
						nivel=1;
						adicionar();
					}
				//}
				
					
				return false;
			
			
			}
			
			@Override
			public boolean onScroll(ScrollEvent arg0) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		
	}	
		
	
	public void adicionar(){
		
		limpou=false;
		int z = osm.getZoomLevel();
		Ponto p;
		if(z>17){
			osm.getOverlays().clear();
			muda=true;
			for(int i=0; i<al[1].size(); i++){
				p = al[1].get(i);	
				addMarker(new GeoPoint(p.getX(),p.getY()), p.getTitle(), p.getDesc(),p.getI());
			}		
		}
		else if(z<=17){
			osm.getOverlays().clear();
			muda=false;
			
			for(int i=0; i<al[0].size(); i++){
				p = al[0].get(i);	
				addMarker(new GeoPoint(p.getX(),p.getY()), p.getTitle(), p.getDesc(),p.getI());
			}
		}
	}
	
	
	public Marker addMarker(GeoPoint center,String t,String d,int i){
		Marker marker = new Marker(osm);
		marker.setPosition(center);
		marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
		if(i!=0)
			marker.setIcon(getResources().getDrawable(i));
		
		marker.setTitle(t);
		marker.setSnippet(d);
		//marker.setSubDescription(d);
		
		
		osm.getOverlays().add(marker);
		
		osm.invalidate();
		return marker;
	}
	
	
	

	
	final static String TARGET_BASE_PATH = "/sdcard/osmdroid/tiles/Google Maps/";

    private void copyFilesToSdCard() {
        copyFileOrDir(""); // copy all files in assets folder in my project
    }

    private void copyFileOrDir(String path) {
        AssetManager assetManager = this.getAssets();
        String assets[] = null;
        try {
            Log.i("tag", "copyFileOrDir() "+path);
            
            assets = assetManager.list(path);
            if (assets.length == 0) {
                copyFile(path);
            } else {
                String fullPath =  TARGET_BASE_PATH + path;
                Log.i("tag", "path="+fullPath);
                File dir = new File(fullPath);
                if (!dir.exists() && !path.startsWith("images") && !path.startsWith("sounds") && !path.startsWith("webkit"))
                    if (!dir.mkdirs())
                        Log.i("tag", "could not create dir "+fullPath);
                for (int i = 0; i < assets.length; ++i) {
                    String p;
                    if (path.equals(""))
                        p = "";
                    else 
                        p = path + "/";

                    if (!path.startsWith("images") && !path.startsWith("sounds") && !path.startsWith("webkit"))
                        copyFileOrDir( p + assets[i]);
                }
            }
        } catch (IOException ex) {
            Log.e("tag", "I/O Exception", ex);
        }
    }

    private void copyFile(String filename) {
        AssetManager assetManager = this.getAssets();

        InputStream in = null;
        OutputStream out = null;
        String newFileName = null;
        try {
            Log.i("tag", "copyFile() "+filename);
            in = assetManager.open(filename);
            if (filename.endsWith(".jpg")) // extension was added to avoid compression on APK file
                newFileName = TARGET_BASE_PATH + filename.substring(0, filename.length()-4);
            else
                newFileName = TARGET_BASE_PATH + filename;
            out = new FileOutputStream(newFileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.e("tag", "Exception in copyFile() of "+newFileName);
            Log.e("tag", "Exception in copyFile() "+e.toString());
        }

    }

	@Override
	public void onLocationChanged(Location location) {
		meuLocal = new GeoPoint(location.getLatitude(), location.getLongitude());
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
