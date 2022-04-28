package uz.pdp.online.newappwarehouse.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.online.newappwarehouse.entity.Attachment;
import uz.pdp.online.newappwarehouse.entity.AttachmentContent;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.repository.AttachmentContentRepository;
import uz.pdp.online.newappwarehouse.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public Result uploadFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        if (file != null) {
            Attachment attachment = new Attachment();
            attachment.setName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            Attachment savedAttachment = attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setAttachment(savedAttachment);
            attachmentContent.setBytes(file.getBytes());
            attachmentContentRepository.save(attachmentContent);
            return new Result("Fayl saqlandi", true, savedAttachment.getId());
        }
        return new Result("Xatolik yuz berdi", false);
    }

    @SneakyThrows
    public Result downloadFile(Integer id, HttpServletResponse response) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) {
            return new Result("Photo id not found", false);
        }
        Attachment attachment = optionalAttachment.get();
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(id);
        if (!optionalAttachmentContent.isPresent())
            return new Result("Content not found", false);
        AttachmentContent attachmentContent = optionalAttachmentContent.get();
        response.setHeader("Content-Disposition", "attachment; fileName:\"" + attachment.getName() + "\"");
        response.setContentType(attachment.getContentType());
        FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
        return new Result("Photo found", true);
    }

    public Result editFile(Integer id, MultipartHttpServletRequest request) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent())
            return new Result("Attachment not found", false);

//        Iterator<String> fileNames = request.getFileNames();
//        MultipartFile file = request.getFile(fileNames.next());
//        if (file != null) {
//            Attachment attachment = optionalAttachment.get();
//            attachment.setName(file.getOriginalFilename());
//            attachment.setSize(file.getSize());
//            attachment.setContentType(file.getContentType());
//            Attachment savedAttachment = attachmentRepository.save(attachment);
//
//            AttachmentContent attachmentContent = new AttachmentContent();
//            attachmentContent.setAttachment(savedAttachment);
//            attachmentContent.setBytes(file.getBytes());
//            attachmentContentRepository.save(attachmentContent);
//            return new Result("File edited", true, savedAttachment.getId());
//        }
        return new Result("File didn't edited", false);

    }

    public Result deleteFile(Integer id) {
        attachmentContentRepository.deleteByAttachmentId(id);
        attachmentRepository.deleteById(id);
        return new Result("File deleted", true);
    }
}
