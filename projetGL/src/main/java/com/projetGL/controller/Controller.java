package com.projetGL.controller;

import java.sql.*;
import java.util.*;

public abstract class Controller<T> {

	/**
	 * Permet de récupérer un objet via son ID
	 * @param id
	 * @return
	 */
	public abstract List<T> returnListe();
	
}