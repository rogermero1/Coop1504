function ChangeCase(elem) {
	elem.value = elem.value.toUpperCase();
}

function abrirEmergente() {
	var ventana = window
			.open(
					"login.jsf",
					"TITULO",
					"width="
							+ screen.availWidth
							+ ", height="
							+ screen.availHeight
							+ ", screenX=0,screenY=0, top=0, left=0, status=yes , resizable=yes, scrollbars=yes");
	ventana.focus();
}

function cerrarEmergente() {
	var temp = window.self;
	temp.opener = window.self;
	temp.close();
}

//document.oncontextmenu = new Function("return false");
//document.onselectstart = new Function("return false");
// document.onmousedown = new Function("return false");

function imprimirSeleccion(ventana, seccion) {
	var ficha = document.getElementById(seccion);
	var ventimp = window.open('', 'popimpr');
	ventimp.document.write(ficha.innerHTML);
	ventimp.document.close();
	ventimp.print();
	ventimp.close();
	ventana.close();
}

function imprimir() {
	// desaparece el boton
	document.getElementById("panelIzquierdo").style.display = 'none';
	// se imprime la pagina
	window.print();
	// reaparece el boton
	document.getElementById("panelIzquierdo").style.display = 'inline';

}