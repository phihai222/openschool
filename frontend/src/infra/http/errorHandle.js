import axios from "axios";

const errorMessage = {
  400: (opt) => `Không tìm thấy: ${opt?.resource || "dữ liệu"}`,
  401: () => "Bạn chưa đăng nhập.",
  500: () => "Lỗi hệ thống, vui lòng thử lại sau.",
};

export const handleApiError = (error, opt) => {
  if (axios.isAxiosError(error)) {
    const status = (error.response && error.response.status) ?? 0;
    const messageFn = errorMessage[status];
    if (messageFn) throw new Error(messageFn(opt));

    throw new Error(`Đã xảy ra lỗi (Mã ${status})`);
  }
  throw new Error("Lỗi không xác định.");
};
