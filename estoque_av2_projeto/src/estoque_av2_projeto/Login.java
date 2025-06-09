package estoque_av2_projeto;

import javax.swing.*;

public class Login {
	public static Usuario realizarLogin() {
		String nome = JOptionPane.showInputDialog("Nome: ");
		String [] opcoesPermissao = { "ADMINISTRADOR", "OPERADOR", "VISUALIZACAO"};
		String permissaoStr = (String) JOptionPane.showInputDialog(
				null,
				"Permissão",
				"Login",
				JOptionPane.QUESTION_MESSAGE,
				null,
				opcoesPermissao,
				opcoesPermissao[0]
				);
		Permissao permissao = Permissao.valueOf(permissaoStr.toUpperCase());
		return new Usuario (nome, 1, permissao);
	}	
}
