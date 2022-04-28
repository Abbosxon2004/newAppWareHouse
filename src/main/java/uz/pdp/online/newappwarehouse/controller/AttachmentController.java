package uz.pdp.online.newappwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request) {
        final Result result = attachmentService.uploadFile(request);
        return result;
    }

    @GetMapping("/downloadById/{id}")
    public Result download(@PathVariable Integer id, HttpServletResponse response) {
        final Result result = attachmentService.downloadFile(id, response);
        return result;
    }

    @PutMapping("/editById/{id}")
    public Result editFile(@PathVariable Integer id, MultipartHttpServletRequest request) throws IOException {
        Result result = attachmentService.editFile(id, request);
        return result;
    }

    @DeleteMapping("/deleteById/{id}")
    public Result delete(@PathVariable Integer id){
        final Result result = attachmentService.deleteFile(id);
        return result;
    }
}
