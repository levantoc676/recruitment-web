package com.web.hr.recruitment.controller;

import com.web.hr.recruitment.entity.Job;
import com.web.hr.recruitment.entity.User;
import com.web.hr.recruitment.repository.UserRepository;
import com.web.hr.recruitment.service.JobService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

  private final JobService jobService;
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/create")
  public String showCreateForm(Model model, Principal principal) {
    String email = principal.getName(); // email login hiện tại
    Optional<User> user = userRepository.findByEmail(email);

    Job job = new Job();
    job.setJobType(""); // hoặc giá trị mặc định "Full-time"

    model.addAttribute("job", job);
    model.addAttribute("isCreatePage", true);
    return "jobs/post-job";
  }

  // Submit form đăng tuyển
  @PostMapping("/create")
  public String createJob(@ModelAttribute("job") Job job,
      @RequestParam(value = "logoFile", required = false) MultipartFile logoFile,
      Principal principal) {
    try {
      // Lấy user hiện tại
      String email = principal.getName();
      User user = userRepository.findByEmail(email).orElseThrow();
      job.setUser(user);

      if (logoFile != null && !logoFile.isEmpty()) {
        // Lưu file logo vào thư mục /uploads
        String uploadDir = "uploads/logos/";
        Files.createDirectories(Paths.get(uploadDir));
        String fileName = System.currentTimeMillis() + "_" + logoFile.getOriginalFilename();
        Path path = Paths.get(uploadDir + fileName);
        Files.write(path, logoFile.getBytes());
        job.setLogoPath("/" + uploadDir + fileName);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    jobService.saveJob(job);
    return "redirect:/jobs/list";
  }

  // Hiển thị danh sách job
  @GetMapping("/list")
  public String listJobs(Model model) {
    List<Job> jobs = jobService.getAllJobs();
    model.addAttribute("jobs", jobs);
    return "jobs/job-list"; // templates/job-list.html
  }

  // Chi tiết job
  @GetMapping("/{id}")
  public String jobDetail(@PathVariable Long id, Model model) {
    jobService.getJobById(id).ifPresent(job -> model.addAttribute("job", job));
    return "jobs/job-detail"; // templates/job-detail.html
  }

  // Xóa job
  @GetMapping("/delete/{id}")
  public String deleteJob(@PathVariable Long id) {
    jobService.deleteJob(id);
    return "redirect:/jobs/list";
  }

  // Hiển thị form edit
  @GetMapping("/edit/{id}")
  public String showEditForm(@PathVariable("id") Long id, Model model) {
    Job job = jobService.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid job Id:" + id));
    model.addAttribute("job", job);
    return "jobs/post-job"; // tên file HTML form edit
  }

  @PostMapping("/update/{id}")
  public String updateJob(
      @PathVariable("id") Long id,
      @ModelAttribute("job") Job jobDetails,
      @RequestParam(value = "logoFile", required = false) MultipartFile logoFile
  ) {
    Job job = jobService.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid job Id: " + id));

    // Cập nhật các trường cơ bản
    job.setTitle(jobDetails.getTitle());
    job.setDescription(jobDetails.getDescription());
    job.setRequirements(jobDetails.getRequirements());
    job.setBenefits(jobDetails.getBenefits());
    job.setSkills(jobDetails.getSkills());
    job.setLocation(jobDetails.getLocation());
    job.setSalaryRange(jobDetails.getSalaryRange());
    job.setHeadcount(jobDetails.getHeadcount());
    job.setIndustry(jobDetails.getIndustry());
    job.setDeadline(jobDetails.getDeadline());
    job.setContactEmail(jobDetails.getContactEmail());
    job.setContactPhone(jobDetails.getContactPhone());
    job.setUpdatedDate(java.time.LocalDateTime.now());

    // Nếu có upload logo mới
//    try {
//      if (logoFile != null && !logoFile.isEmpty()) {
//        byte[] logoBytes = logoFile.getBytes();
//        job.setLogo(logoBytes); // hoặc lưu path nếu bạn cấu hình upload ra file
//      }
//    } catch (Exception e) {
//      e.printStackTrace();
//      // có thể log warning nhưng không chặn update
//    }

    jobService.saveJob(job);
    return "redirect:/jobs/list"; // hoặc index tùy flow của bạn
  }
}
