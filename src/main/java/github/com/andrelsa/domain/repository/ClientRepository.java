package github.com.andrelsa.domain.repository;

import github.com.andrelsa.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientRepository {
	
	private static String INSERT = "INSERT INTO CLIENTE (nome) VALUES (?)";
	private static String LIST_ALL = "SELECT * FROM CLIENTE";
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Cliente save(Cliente cliente) {
		jdbcTemplate.update(INSERT, new Object[] {cliente.getName()});
		return cliente;
	}
	
	public List<Cliente> getAll() {
		return jdbcTemplate.query(LIST_ALL, new RowMapper<Cliente>() {
			@Override
			public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
				Integer id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				return new Cliente(id, nome);
			}
		});
	}
	
}
