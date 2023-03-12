package com.baeldung.scopedvalues.classic;

import com.baeldung.scopedvalues.data.Data;
import com.baeldung.scopedvalues.data.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class Controller {

    private final Service service = new Service();

    public void processRequest(HttpServletRequest request, HttpServletResponse response, User loggedInUser) throws IOException {
        Optional<Data> data = service.getData(request, loggedInUser);
        if (data.isPresent()) {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            out.print(data.get());
            out.flush();
            response.setStatus(200);
        } else {
            response.setStatus(400);
        }
    }

}
