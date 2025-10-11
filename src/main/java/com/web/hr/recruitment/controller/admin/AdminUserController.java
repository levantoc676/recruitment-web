package com.web.hr.recruitment.controller.admin;

import com.web.hr.recruitment.entity.user.User;
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

  // Hiển thị form nhập email
  @GetMapping("/forgot-password")
  public String showForgotPasswordForm(Model model) {
    return "auth/forgot-password"; // Trỏ tới file forgot-password.html
  }

  // Xử lý gửi link reset
  @PostMapping("/forgot-password")
  public String forgotPassword(@RequestParam String email, Model model) {
    boolean result = userService.createPasswordResetToken(email);

    if (result) {
      model.addAttribute("message", "Link đặt lại mật khẩu đã được gửi đến email của bạn.");
    } else {
      model.addAttribute("error", "Email không tồn tại trong hệ thống.");
    }

    return "auth/forgot-password";
  }

  @GetMapping("/reset-password")
  public String showResetPasswordForm(@RequestParam String token, Model model) {
    String result = userService.validatePasswordResetToken(token);

    if (result != null) {
      // token invalid hoặc expired
      model.addAttribute("error", "Link đặt lại mật khẩu không hợp lệ hoặc đã hết hạn.");
      return "forgot_password"; // quay lại trang quên mật khẩu
    }

    // token hợp lệ → truyền xuống form reset
    model.addAttribute("token", token);
    return "auth/reset_password"; // hiển thị form reset_password.html
  }

  @PostMapping("/reset-password")
  public String resetPassword(@RequestParam String token,
      @RequestParam String newPassword,
      @RequestParam String confirmPassword,
      Model model) {
    // Kiểm tra token
    String result = userService.validatePasswordResetToken(token);
    if (result != null) {
      model.addAttribute("error", "Token không hợp lệ hoặc đã hết hạn.");
      return "reset_password";
    }

    // Kiểm tra confirm password
    if (!newPassword.equals(confirmPassword)) {
      model.addAttribute("error", "Mật khẩu xác nhận không khớp.");
      model.addAttribute("token", token);
      return "reset_password";
    }

    // Reset mật khẩu
    userService.resetPassword(token, newPassword);
    model.addAttribute("message", "Đặt lại mật khẩu thành công. Bạn có thể đăng nhập ngay.");
    return "redirect:/auth/login";
  }
}
