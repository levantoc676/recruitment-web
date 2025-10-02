package com.web.hr.recruitment.controller.admin;

import com.web.hr.recruitment.entity.User;
import com.web.hr.recruitment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminUserController {

  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public String listUsers(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(required = false) String sort,
      @RequestParam(required = false) String role,
      @RequestParam(required = false) String q,
      Model model
  ) {
    Page<User> users = userService.getUsers(page, size, sort, role, q);

    model.addAttribute("users", users.getContent());
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", users.getTotalPages());
    model.addAttribute("q", q);
    model.addAttribute("role", role);

    return "admin/user-list"; // trỏ tới file user-list.html
  }

  // Form tạo User
  @GetMapping("/users/create")
  public String createUserForm(Model model) {
    model.addAttribute("user", new User());
    return "admin/user-form"; // file user-form.html
  }

  // Form chỉnh sửa user
  @GetMapping("/users/edit/{id}")
  public String editUserForm(@PathVariable("id") Long id, Model model) {
    User user = userService.getUserById(id); // lấy user từ DB
    if (user == null) {
      return "redirect:/admin/users"; // nếu không tồn tại, quay về danh sách
    }
    model.addAttribute("user", user);
    return "admin/user-form";
  }

  // Xử lý lưu user (cả add + edit)
  @PostMapping("/users/save")
  public String saveUser(@ModelAttribute("user") User user) {
    userService.saveOrUpdateUser(user);
    return "redirect:/admin/users";
  }

  @GetMapping("/users/delete/{id}")
  public String deleteUser(@PathVariable("id") Long userId, RedirectAttributes redirectAttributes) {
    try {
      userService.deleteUser(userId);
      redirectAttributes.addFlashAttribute("success", "Xóa user thành công");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("error", "Xóa user thất bại: " + e.getMessage());
    }
    return "redirect:/admin/users";
  }

  @PostMapping("/users/lock/{userId}")
  public String lockUser(@PathVariable Long userId, RedirectAttributes redirectAttributes) {
    userService.lockUser(userId);
    redirectAttributes.addFlashAttribute("message", "User đã bị khóa");
    return "redirect:/admin/users";
  }

  @PostMapping("/users/unlock/{userId}")
  public String unlockUser(@PathVariable Long userId, RedirectAttributes redirectAttributes) {
    userService.unlockUser(userId);
    redirectAttributes.addFlashAttribute("message", "User đã được mở khóa");
    return "redirect:/admin/users";
  }
}
