package github.com.andrelsa.domain.repository;

import github.com.andrelsa.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientRepository {
	
	private static String INSERT = "INSERT INTO CLIENTE (nome) VALUES (?)";
	private static String UPDATE = "UPDATE CLIENTE SET NOME = ? WHERE ID = ?";
	private static String DELETE = "DELETE FROM CLIENTE WHERE ID = ?";
	private static String LIST_ALL = "SELECT * FROM CLIENTE";
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Cliente save(Cliente cliente) {
		jdbcTemplate.update(INSERT, new Object[] {cliente.getName()});
		return cliente;
	}
	
	public Cliente update(Cliente cliente) {
		jdbcTemplate.update(UPDATE, new Object[] {cliente.getName(), cliente.getId()});
		return cliente;
	}
	
	public void delete(Cliente cliente) {
		delete(cliente.getId());
		
	}
	
	public void delete(Integer id) {
		jdbcTemplate.update(DELETE, new Object[] {id});
	}
	
	public List<Cliente> getByName(String name) {
		return jdbcTemplate.query(LIST_ALL.concat(" WHERE NOME LIKE ? "), new Object[]{"%" + name + "%"},
				getClientRowMapper());
	}
	
	public List<Cliente> getAll() {
		return jdbcTemplate.query(LIST_ALL, getClientRowMapper());
	}
	
	private RowMapper<Cliente> getClientRowMapper() {
		return (resultSet, i) -> {
			Integer id = resultSet.getInt("id");
			String nome = resultSet.getString("nome");
			return new Cliente(id, nome);
		};
	}
	
}
