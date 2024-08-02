package com.example.duan1_moviewwbooking.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName = "MTBA";
    static final int dbVersion = 2;
    public static final String TABLE_NAME_PHIM = "phim";
    public static final String TABLE_NAME_TAIKHOAN = "taikhoan";
    public static final String TABLE_NAME_RAPCHIEU = "rapchieu";
    public static final String TABLE_NAME_XUATCHIEU = "xuatchieu";
    public static final String TABLE_NAME_GHENGOI = "ghengoi";
    public static final String TABLE_NAME_VEPHIM = "vephim";

    public DbHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTablePhim =
                "CREATE TABLE " + TABLE_NAME_PHIM + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenPhim TEXT NOT NULL, " +
                        "moTa TEXT NOT NULL, " +
                        "thoiLuong INTEGER NOT NULL, " +
                        "anhDaiDien TEXT NOT NULL, " +
                        "trailer TEXT NOT NULL, " +
                        "doTuoiXem INTEGER NOT NULL, " +
                        "ngayPhatHanh TEXT NOT NULL)";
        db.execSQL(createTablePhim);

        String setPhim1 = "INSERT INTO phim VALUES(1, 'CHIEM DOAT', 'Kể về người vợ của một gia đình thượng lưu thuê cô bảo mẫu “trong mơ” để chăm sóc con trai mình. Nhưng cô không ngờ rằng, phía sau sự trong sáng, tinh khiết kia, cô bảo mẫu luôn che giấu âm mưu nhằm phá hoại hạnh phúc gia đình và khiến cuộc sống của cô thay đổi mãi mãi.', " +
                "113, 'https://www.cgv.vn/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/6/7/675wx1000h-chiem-doat.jpg', 'https://www.youtube.com/watch?v=vFJpPawJWjw', 18, '2023/11/24')";
        db.execSQL(setPhim1);

        String setPhim2 = "INSERT INTO phim VALUES(2, 'DIEU UOC', '“Wish” là bộ phim hoạt hình kỷ niệm 100 năm thành lập của Walt Disney Studios (sự kiện toàn cầu D100). \"Điều Ước\" lấy bối cảnh tại một vương quốc diệu kỳ tên Rosas. Ở đây, Asha - một cô gái thông minh và mơ mộng đã ước một điều ước quá sức mạnh mẽ, đến nỗi một thế lực ngoài vũ trụ với sức mạnh vô hạn tên Star đã đáp lại cô. Asha và Star cùng nhau đối mặt với người cầm quyền của Rosas - quốc vương Magnifico, để cứu lấy mọi người và chứng minh rằng ý chí gan dạ của con người kết hợp với phép màu của những vì sao sẽ giúp những điều kỳ diệu xảy ra.', " +
                "94,'https://www.cgv.vn/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/p/a/payoff_poster-wish.jpg', 'https://www.youtube.com/watch?v=uDtF6D5BePs&t=5s', 16, '2023/11/25')";
        db.execSQL(setPhim2);

        String setPhim3 = "INSERT INTO phim VALUES(3, 'DAU TRUONG SINH TU', 'Đấu Trường Sinh Tử: Khúc Hát Của Chim Ca Và Rắn Độc là phần tiền truyện của mạch truyện chính, lấy bối cảnh 64 năm trước phần phim đầu tiên. Mạch phim theo chân Coriolanus Snow trẻ tuổi, chính là vị Tổng thống Snow trong loạt phim sau này. Lúc này Coriolanus là niềm hy vọng cuối cùng cho dòng dõi Snow đang dần lụi tàn. Được chỉ định trở thành cố vấn cho trò chơi, vai trò của anh là trợ giúp vật tế trong đấu trường và anh sẽ là cố vấn của Lucy Gray Baird - một cô gái đặc biệt đến từ Quận 12.', " +
                "157, 'https://www.cgv.vn/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/p/o/poster_payoff_1_.jpg', 'https://www.youtube.com/watch?v=QsEfBzLoKys', 16, '30/01/2022')";
        db.execSQL(setPhim3);

        String createTableTaiKhoan =
                "CREATE TABLE " + TABLE_NAME_TAIKHOAN + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenTaiKhoan TEXT NOT NULL, " +
                        "hoTen TEXT NOT NULL, " +
                        "matKhau TEXT NOT NULL)";
        db.execSQL(createTableTaiKhoan);

        String setAdmin = "INSERT INTO taikhoan VALUES(1, 'admin', 'Admin', 'admin')";
        db.execSQL(setAdmin);

        String createTableRapChieu =
                "CREATE TABLE " + TABLE_NAME_RAPCHIEU + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenRap TEXT NOT NULL, " +
                        "diaChi TEXT NOT NULL, " +
                        "dienThoai TEXT NOT NULL, " +
                        "ngayChieu TEXT NOT NULL)";
        db.execSQL(createTableRapChieu);

        String setRapChieu1 = "INSERT INTO rapchieu VALUES(1, 'CGV VINCOM PLAZA', 'HaNoi', '0999666333', '2023-12-07')";
        db.execSQL(setRapChieu1);
        String setRapChieu2 = "INSERT INTO rapchieu VALUES(2, 'CGV Pamulang Timur', 'HaNoi', '0111222333', '2023-12-08')";
        db.execSQL(setRapChieu2);
        String setRapChieu3 = "INSERT INTO rapchieu VALUES(3, 'CGV Cresent Mall', 'Ho Chi Minh', '022334455', '2023-12-08')";
        db.execSQL(setRapChieu3);
        String setRapChieu4 = "INSERT INTO rapchieu VALUES(4, 'CGV Times City', 'HaNoi', '0243333223', '2023-12-08')";
        db.execSQL(setRapChieu4);
        String setRapChieu5 = "INSERT INTO rapchieu VALUES(5, 'CGV Vincom Mega Mall', 'HaNoi', '023423432', '2023-12-09')";
        db.execSQL(setRapChieu5);
        String setRapChieu6 = "INSERT INTO rapchieu VALUES(6, 'CGV Vincom Landmark 81', 'Ho Chi Minh', '08453523', '2023-12-09')";
        db.execSQL(setRapChieu6);

        String createTableXuatChieu =
                "CREATE TABLE " + TABLE_NAME_XUATCHIEU + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "idRapChieu INTEGER REFERENCES rapchieu(idRapChieu) NOT NULL, " +
                        "thoiGianBatDau TEXT NOT NULL, " +
                        "giaVe INTEGER NOT NULL)";
        db.execSQL(createTableXuatChieu);

        String setXuatChieu1 = "INSERT INTO xuatchieu VALUES(1, 1, '16:00', 50000)";
        db.execSQL(setXuatChieu1);
        String setXuatChieu2 = "INSERT INTO xuatchieu VALUES(2, 1, '18:00', 70000)";
        db.execSQL(setXuatChieu2);
        String setXuatChieu3 = "INSERT INTO xuatchieu VALUES(3, 2, '20:00', 80000)";
        db.execSQL(setXuatChieu3);
        String setXuatChieu4 = "INSERT INTO xuatchieu VALUES(4, 2, '21:00', 70000)";
        db.execSQL(setXuatChieu4);
        String setXuatChieu5 = "INSERT INTO xuatchieu VALUES(5, 2, '22:00', 90000)";
        db.execSQL(setXuatChieu5);
        String setXuatChieu6 = "INSERT INTO xuatchieu VALUES(6, 3, '18:00', 50000)";
        db.execSQL(setXuatChieu6);
        String setXuatChieu7 = "INSERT INTO xuatchieu VALUES(7, 3, '20:00', 70000)";
        db.execSQL(setXuatChieu7);
        String setXuatChieu8 = "INSERT INTO xuatchieu VALUES(8, 4, '21:00', 70000)";
        db.execSQL(setXuatChieu8);
        String setXuatChieu9 = "INSERT INTO xuatchieu VALUES(9, 4, '22:00', 90000)";
        db.execSQL(setXuatChieu9);
        String setXuatChieu10 = "INSERT INTO xuatchieu VALUES(10, 5, '21:00', 70000)";
        db.execSQL(setXuatChieu10);
        String setXuatChieu11 = "INSERT INTO xuatchieu VALUES(11, 5, '22:00', 90000)";
        db.execSQL(setXuatChieu11);
        String setXuatChieu12 = "INSERT INTO xuatchieu VALUES(12, 6, '12:00', 50000)";
        db.execSQL(setXuatChieu12);
        String setXuatChieu13 = "INSERT INTO xuatchieu VALUES(13, 6, '15:00', 60000)";
        db.execSQL(setXuatChieu13);

        String createTableGheNgoi =
                "CREATE TABLE " + TABLE_NAME_GHENGOI + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "idXuatChieu INTEGER REFERENCES xuatchieu(idXuatChieu) NOT NULL, " +
                        "soGhe TEXT NOT NULL, " +
                        "trangThai INTEGER NOT NULL)";
        db.execSQL(createTableGheNgoi);

        String setGheNgoi1IdXuatChieu1 = "INSERT INTO ghengoi VALUES(1, 1, 'A1', 0)";
        db.execSQL(setGheNgoi1IdXuatChieu1);
        String setGheNgoi2IdXuatChieu1 = "INSERT INTO ghengoi VALUES(2, 1, 'A2', 0)";
        db.execSQL(setGheNgoi2IdXuatChieu1);
        String setGheNgoi3IdXuatChieu1 = "INSERT INTO ghengoi VALUES(3, 1, 'A3', 0)";
        db.execSQL(setGheNgoi3IdXuatChieu1);
        String setGheNgoi4IdXuatChieu1 = "INSERT INTO ghengoi VALUES(4, 1, 'A4', 0)";
        db.execSQL(setGheNgoi4IdXuatChieu1);
        String setGheNgoi5IdXuatChieu1 = "INSERT INTO ghengoi VALUES(5, 1, 'A5', 0)";
        db.execSQL(setGheNgoi5IdXuatChieu1);
        String setGheNgoi6IdXuatChieu1 = "INSERT INTO ghengoi VALUES(6, 1, 'B1', 0)";
        db.execSQL(setGheNgoi6IdXuatChieu1);
        String setGheNgoi7IdXuatChieu1 = "INSERT INTO ghengoi VALUES(7, 1, 'B2', 0)";
        db.execSQL(setGheNgoi7IdXuatChieu1);

        String setGheNgoi1IdXuatChieu3 = "INSERT INTO ghengoi VALUES(8, 3, 'A1', 0)";
        db.execSQL(setGheNgoi1IdXuatChieu3);
        String setGheNgoi2IdXuatChieu3 = "INSERT INTO ghengoi VALUES(9, 3, 'A2', 0)";
        db.execSQL(setGheNgoi2IdXuatChieu3);
        String setGheNgoi3IdXuatChieu3 = "INSERT INTO ghengoi VALUES(10, 3, 'A3', 0)";
        db.execSQL(setGheNgoi3IdXuatChieu3);
        String setGheNgoi4IdXuatChieu3 = "INSERT INTO ghengoi VALUES(11, 3, 'A4', 0)";
        db.execSQL(setGheNgoi4IdXuatChieu3);
        String setGheNgoi5IdXuatChieu3 = "INSERT INTO ghengoi VALUES(12, 3, 'A5', 0)";
        db.execSQL(setGheNgoi5IdXuatChieu3);
        String setGheNgoi6IdXuatChieu3 = "INSERT INTO ghengoi VALUES(13, 3, 'B1', 0)";
        db.execSQL(setGheNgoi6IdXuatChieu3);
        String setGheNgoi7IdXuatChieu3 = "INSERT INTO ghengoi VALUES(14, 3, 'B2', 0)";
        db.execSQL(setGheNgoi7IdXuatChieu3);
        String setGheNgoi8IdXuatChieu3 = "INSERT INTO ghengoi VALUES(15, 3, 'B3', 0)";
        db.execSQL(setGheNgoi8IdXuatChieu3);
        String setGheNgoi9IdXuatChieu3 = "INSERT INTO ghengoi VALUES(16, 3, 'B4', 0)";
        db.execSQL(setGheNgoi9IdXuatChieu3);
        String setGheNgoi10IdXuatChieu3 = "INSERT INTO ghengoi VALUES(17, 3, 'B5', 0)";
        db.execSQL(setGheNgoi10IdXuatChieu3);
        String setGheNgoi11IdXuatChieu3 = "INSERT INTO ghengoi VALUES(18, 3, 'C1', 0)";
        db.execSQL(setGheNgoi11IdXuatChieu3);
        String setGheNgoi12IdXuatChieu3 = "INSERT INTO ghengoi VALUES(19, 3, 'C2', 0)";
        db.execSQL(setGheNgoi12IdXuatChieu3);
        String setGheNgoi13IdXuatChieu3 = "INSERT INTO ghengoi VALUES(20, 3, 'C3', 0)";
        db.execSQL(setGheNgoi13IdXuatChieu3);
        String setGheNgoi14IdXuatChieu3 = "INSERT INTO ghengoi VALUES(21, 3, 'C4', 0)";
        db.execSQL(setGheNgoi14IdXuatChieu3);
        String setGheNgoi15IdXuatChieu3 = "INSERT INTO ghengoi VALUES(22, 3, 'C5', 0)";
        db.execSQL(setGheNgoi15IdXuatChieu3);
        String setGheNgoi16IdXuatChieu3 = "INSERT INTO ghengoi VALUES(23, 3, 'D1', 0)";
        db.execSQL(setGheNgoi16IdXuatChieu3);
        String setGheNgoi17IdXuatChieu3 = "INSERT INTO ghengoi VALUES(24, 3, 'D2', 0)";
        db.execSQL(setGheNgoi17IdXuatChieu3);
        String setGheNgoi18IdXuatChieu3 = "INSERT INTO ghengoi VALUES(25, 3, 'D3', 0)";
        db.execSQL(setGheNgoi18IdXuatChieu3);
        String setGheNgoi19IdXuatChieu3 = "INSERT INTO ghengoi VALUES(26, 3, 'D4', 0)";
        db.execSQL(setGheNgoi19IdXuatChieu3);
        String setGheNgoi20IdXuatChieu3 = "INSERT INTO ghengoi VALUES(27, 3, 'D5', 0)";
        db.execSQL(setGheNgoi20IdXuatChieu3);

        String setGheNgoi1IdXuatChieu6 = "INSERT INTO ghengoi VALUES(28, 6, 'A1', 0)";
        db.execSQL(setGheNgoi1IdXuatChieu6);
        String setGheNgoi2IdXuatChieu6 = "INSERT INTO ghengoi VALUES(29, 6, 'A2', 0)";
        db.execSQL(setGheNgoi2IdXuatChieu6);
        String setGheNgoi3IdXuatChieu6 = "INSERT INTO ghengoi VALUES(30, 6, 'A3', 0)";
        db.execSQL(setGheNgoi3IdXuatChieu6);
        String setGheNgoi4IdXuatChieu6 = "INSERT INTO ghengoi VALUES(31, 6, 'A4', 0)";
        db.execSQL(setGheNgoi4IdXuatChieu6);
        String setGheNgoi5IdXuatChieu6 = "INSERT INTO ghengoi VALUES(32, 6, 'A5', 0)";
        db.execSQL(setGheNgoi5IdXuatChieu6);

        String setGheNgoi1IdXuatChieu10 = "INSERT INTO ghengoi VALUES(33, 10, 'A1', 0)";
        db.execSQL(setGheNgoi1IdXuatChieu10);
        String setGheNgoi2IdXuatChieu10 = "INSERT INTO ghengoi VALUES(34, 10, 'A2', 0)";
        db.execSQL(setGheNgoi2IdXuatChieu10);
        String setGheNgoi3IdXuatChieu10 = "INSERT INTO ghengoi VALUES(35, 10, 'A3', 0)";
        db.execSQL(setGheNgoi3IdXuatChieu10);
        String setGheNgoi4IdXuatChieu10 = "INSERT INTO ghengoi VALUES(36, 10, 'A4', 0)";
        db.execSQL(setGheNgoi4IdXuatChieu10);
        String setGheNgoi5IdXuatChieu10 = "INSERT INTO ghengoi VALUES(37, 10, 'A5', 0)";
        db.execSQL(setGheNgoi5IdXuatChieu10);
        String setGheNgoi6IdXuatChieu10 = "INSERT INTO ghengoi VALUES(38, 10, 'B1', 0)";
        db.execSQL(setGheNgoi6IdXuatChieu10);

        String createTableVePhim =
                "CREATE TABLE " + TABLE_NAME_VEPHIM + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "idXuatChieu INTEGER REFERENCES xuatchieu(idXuatChieu) NOT NULL, " +
                        "idTaiKhoan INTEGER  NOT NULL, " +
                        "tongGiaTri INTEGER  NOT NULL, " +
                        "ngayDatVe DATE NOT NULL)";
        db.execSQL(createTableVePhim);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTablePhim = "drop table if exists phim";
        db.execSQL(dropTablePhim);
        String dropTableNguoiDung = "drop table if exists nguoidung";
        db.execSQL(dropTableNguoiDung);
        String dropTableRapChieu = "drop table if exists rapchieu";
        db.execSQL(dropTableRapChieu);
        String dropTableXuatChieu = "drop table if exists xuatchieu";
        db.execSQL(dropTableXuatChieu);
        String dropTableGheNgoi = "drop table if exists ghengoi";
        db.execSQL(dropTableGheNgoi);
        String dropTableTaiKhoan = "drop table if exists taikhoan";
        db.execSQL(dropTableTaiKhoan);
        String dropTableVePhim = "drop table if exists vephim";
        db.execSQL(dropTableVePhim);
        onCreate(db);

    }
}
