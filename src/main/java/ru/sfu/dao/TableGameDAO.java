package ru.sfu.dao;

import org.springframework.stereotype.Component;
import ru.sfu.model.TableGame;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;


@Component
public class TableGameDAO  {
    JdbcTemplate jdbcTemplate;

    public void setDateSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<TableGame> findAll() {
        return jdbcTemplate.query("select * from table_games ORDER BY table_games.id",
                new BeanPropertyRowMapper<>(TableGame.class));
    }

    public void insert(TableGame tg){
        jdbcTemplate.update(
                "insert into table_games (gamename, price, playeramount, genre) " +
                        "values(?, ?, ?, ?)",
                tg.getGameName(),
                tg.getPrice(),
                tg.getPlayerAmount(),
                tg.getGenre()
        );
    }

    public void edit(TableGame tg){
        jdbcTemplate.update(
                "update table_games " +
                        "set gamename=?, price=?, playeramount=?, genre=?" +
                        "where id=?",
                tg.getGameName(),
                tg.getPrice(),
                tg.getPlayerAmount(),
                tg.getGenre(),
                tg.getId());
    }

    public TableGame get(int id){
        return jdbcTemplate.query("SELECT * FROM table_games WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(TableGame.class))
                .stream().findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from table_games where id=?", id);
    }

    public List<TableGame> findGameBelowPrice(int upperPrice){
        return jdbcTemplate.query(
                "select * from table_games where price<?",
                new Object[]{upperPrice},
                new BeanPropertyRowMapper<>(TableGame.class));
    }
}
