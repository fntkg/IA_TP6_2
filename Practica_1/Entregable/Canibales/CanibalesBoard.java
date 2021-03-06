package aima.core.environment.Canibales;

import java.util.Arrays;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;

public class CanibalesBoard {

	public static Action M2C = new DynamicAction("M2C");

	public static Action M2M = new DynamicAction("M2M");

	public static Action M1C = new DynamicAction("M1C");

	public static Action M1M = new DynamicAction("M1M");
	
	public static Action M1M1C = new DynamicAction("M1M1C");

	private int[] state;

	//
	// PUBLIC METHODS
	//

	public CanibalesBoard() { //0 es orilla izquierda, 1 es orilla derecha (PARA EL BOTE)
		state = new int[] { 3, 3, 0, 0, 0};
	}

	public CanibalesBoard(int[] state) {
		this.state = new int[state.length];
		System.arraycopy(state, 0, this.state, 0, state.length);
	}

	public CanibalesBoard(CanibalesBoard copyBoard) {
		this(copyBoard.getState());
	}

	public int[] getState() {
		return state;
	}

	public void move2C() {
		if (state[2] == 0) { //Si el bote esta a la izquierda
			state[1] = state[1] - 2; //Quito 2 canibales de la izquierda
			state[4] = state [4] + 2; //A�ado 2 canibales a la derecha
			state[2] = 1; //Muevo el bote a la derecha
		}
		else {
			state[1] = state[1] + 2; //	A�ado 2 canibales de la izquierda
			state[4] = state [4] - 2; //Quito 2 canibales a la derecha
			state[2] = 0; //Muevo el bote a la izquierda
		}
	}
	
	public void move2M() {
		if (state[2] == 0) { //Si el bote esta a la izquierda
			state[0] = state[0] - 2; //Quito 2 misioneros de la izquierda
			state[3] = state [3] + 2; //A�ado 2 misioneros a la derecha
			state[2] = 1; //Muevo el bote a la derecha
		}
		else {
			state[0] = state[0] + 2; //	A�ado 2 misioneros de la izquierda
			state[3] = state [3] - 2; //Quito 2 misioneros a la derecha
			state[2] = 0; //Muevo el bote a la izquierda
		}
	}
	
	public void move1C() {
		if (state[2] == 0) { //Si el bote esta a la izquierda
			state[1] = state[1] - 1; //Quito 1 canibales de la izquierda
			state[4] = state [4] + 1; //A�ado 1 canibales a la derecha
			state[2] = 1; //Muevo el bote a la derecha
		}
		else {
			state[1] = state[1] + 1; //	A�ado 1 canibales de la izquierda
			state[4] = state [4] - 1; //Quito 1 canibales a la derecha
			state[2] = 0; //Muevo el bote a la izquierda
		}
	}
	
	public void move1M() {
		if (state[2] == 0) { //Si el bote esta a la izquierda
			state[0] = state[0] - 1; //Quito 1 misioneros de la izquierda
			state[3] = state [3] + 1; //A�ado 1 misioneros a la derecha
			state[2] = 1; //Muevo el bote a la derecha
		}
		else {
			state[0] = state[0] + 1; //	A�ado 1 misioneros de la izquierda
			state[3] = state [3] - 1; //Quito 1 misioneros a la derecha
			state[2] = 0; //Muevo el bote a la izquierda
		}
	}
	
	public void move1M1C() {
		if (state[2] == 0) { //Si el bote esta a la izquierda
			state[2] = 1; //Muevo el bote a la derecha
			state[1] = state[1] - 1;
			state[0] = state[0] - 1;
			state[3] = state[3] + 1;
			state[4] = state[4] + 1;
		}
		else {
			state[2] = 0; //Muevo el bote a la izquierda
			state[1] = state[1] + 1;
			state[0] = state[0] + 1;
			state[3] = state[3] - 1;
			state[4] = state[4] - 1;
		}
	}


	public boolean canMoveGap(Action where) {
		boolean retVal = true;
		if (where.equals(M1C)) {
			if (state[2] == 0) { //Si el bote esta en la orilla izquierda
				if (!(state[1] >= 1)) { retVal = false; }
				else if (!(state[3] >= ( state[4] + 1) || state [3] == 0)) { retVal = false; }
			}
			else {//Si el bote esta en la orilla derecha
				if (!(state[4] >= 1)) { retVal = false; }
				else if (!(state[0] >= ( state[1] + 1) || state [0] == 0)) { retVal = false; }
			}
		}
		else if (where.equals(M2C)) {
			if (state[2] == 0) {
				if (!(state[1] >= 2)) { retVal = false; }
				else if (!(state[3] >= ( state[4] + 2) || state [3] == 0)) { retVal = false; }
			}
			else{
				if (!(state[4] >= 2)) { retVal = false; }
				else if (!(state[0] >= ( state[1] + 2) || state [0] == 0)) { retVal = false; }
			}
		}
		else if (where.equals(M1M)) {
			if (state[2] == 0) {
				if (!(state[0] >= 1)) { retVal = false; }
				else if (!((state[0] >= (state[1] + 1) || state[0] == 1) && (state[3] >= state[4] - 1))) { retVal = false; }
			}
			else {
				if (!(state[3] >= 1)) { retVal = false; }
				else if (!((state[3] >= (state[4] + 1) || state[3] == 1) && (state[0] >= state[1] - 1))) { retVal = false; }
			}
		}
		else if (where.equals(M2M)) {
			if (state[2] == 0) {
				if (!(state[0] >= 2)) { retVal = false; }
				else if (!((state[0] >= (state[1] + 2) || state[0] == 2) && (state[3] >= state[4] - 2))) { retVal = false; }
			}
			else {
				if (!(state[3] >= 2)) { retVal = false; }
				else if (!((state[3] >= (state[4] + 2) || state[3] == 2) && (state[0] >= state[1] - 2))) { retVal = false; }
			}
		}
		else if (where.equals(M1M1C)) {
			if (state[2] == 0) {
				if (!(state[0] >= 1)) { retVal = false; }
				else if (!(state[1] >= 1)) { retVal = false; }
				else if (!(state[3] >= state[4])) { retVal = false; }
			}
			else {
				if (!(state[3] >= 1)) { retVal = false; }
				else if (!(state[4] >= 1)) { retVal = false; }
				else if (!(state[0] >= state[1])) { retVal = false; }
			}
		}
		
		return retVal;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(state);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CanibalesBoard other = (CanibalesBoard) obj;
		if (!Arrays.equals(state, other.state))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		String valor[] = {"RIBERA-IZQ"," "," "," "," ", " "," ", "    ", "--RIO--", "    ", " "," "," "," ", " "," ", "RIBERA-DCHA"};
		int estado[]=state;
		int m = 3;
		for (int i = estado[0]; i > 0; i--) {
			valor[m] = "M";
			m--;
		}
		m = 6;
		for (int i = estado[1]; i > 0; i--) {
			valor[m] = "C";
			m--;
		}
		if (estado[2] == 0) {
			valor[7] = "BOTE";
		}
		else {
			valor[9] = "BOTE";
		}
		m = 12;
		for (int i = estado[3]; i > 0; i--) {
			valor[m] = "M";
			m--;
		}
		m = 15;
		for (int i = estado[4]; i > 0; i--) {
			valor[m] = "C";
			m--;
		}
		String str = Arrays.toString(valor);
	    str = str.substring(1, str.length()-1).replace(",", "");
		
		return str;
	}
	
}