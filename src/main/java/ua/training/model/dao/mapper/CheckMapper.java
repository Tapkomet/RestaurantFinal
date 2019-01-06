package ua.training.model.dao.mapper;

import ua.training.model.entity.Check;
import ua.training.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class CheckMapper implements ObjectMapper<Check> {


    @Override
    public Check extractFromResultSet(ResultSet rs) throws SQLException {
        Check check = new Check();
        check.setId(rs.getInt("check_id"));
        check.setTotalPrice(rs.getLong("total_price"));
        check.setCreateTime(rs.getTimestamp("create_time"));
        User client = new User();
        client.setId(rs.getInt("client_id"));
        check.setClient(client);
        check.setConfirmed(rs.getBoolean("is_confirmed"));
        User admin = new User();
        admin.setId(rs.getInt("admin_id"));
        check.setAdmin(admin);
        check.setCheck(rs.getBoolean("is_check"));
        check.setPaid(rs.getBoolean("is_paid"));
        check.setTip(rs.getLong("tip"));
        return check;
    }

    public Check extractOneFromResultSet(ResultSet rs) throws SQLException {
        Check check = new Check();
        check.setId(rs.getInt("check_id"));
        check.setTotalPrice(rs.getLong("total_price"));
        check.setCreateTime(rs.getTimestamp("create_time"));
        return check;
    }

    @Override
    public Check makeUnique(Map<Integer, Check> cache,
                            Check check) {
        cache.putIfAbsent(check.getId(), check);
        return cache.get(check.getId());
    }
}