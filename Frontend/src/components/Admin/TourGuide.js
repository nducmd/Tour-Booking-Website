import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { searchTourGuides, getTourguideStats } from "../../api/AdminApi";
import { CSVLink } from "react-csv";
import MoneyFormat from "../Custom/MoneyFormat";
export default function TourGuide() {
  const [tourguideList, setTourguideList] = useState([]);
  const [tourguideStats, setTourguideStats] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [totalElements, setTotalElements] = useState(0);

  const [searchKeywordTourGuide, setSearchKeywordTourGuide] = useState("");
  useEffect(() => {
    const getTourguidePage = async () => {
      const tmpTourguidePage = await searchTourGuides(
        searchKeywordTourGuide,
        currentPage
      );
      setTourguideList(tmpTourguidePage ? tmpTourguidePage.content : []);
      setTotalPages(tmpTourguidePage ? tmpTourguidePage.totalPages : 0);
      setTotalElements(tmpTourguidePage ? tmpTourguidePage.totalElements : 0);
    };
    getTourguidePage();
  }, [currentPage, searchKeywordTourGuide]);

  useEffect(() => {
    const fetchTourGuidesStats = async () => {
      const tmpTouguideStats = await getTourguideStats();
      setTourguideStats(tmpTouguideStats);
    };
    fetchTourGuidesStats();
  }, []);

  const handleNextPage = () => {
    setCurrentPage(
      currentPage + 1 < totalPages ? currentPage + 1 : currentPage
    );
  };

  const handlePrevPage = () => {
    setCurrentPage(currentPage > 0 ? currentPage - 1 : 0);
  };

  const handleSearchTourGuideChange = (event) => {
    setCurrentPage(0);
    setSearchKeywordTourGuide(event.target.value);
  };

  return (
    <main
      id="content"
      role="main"
      className="main pointer-event"
      style={{ overflowY: "auto" }}
    >
      <div className="content container-fluid">
        {/* Page Header */}
        <div className="page-header">
          <div className="row align-items-center">
            <div className="col-sm mb-2 mb-sm-0">
              <h1 className="page-header-title">Hướng Dẫn Viên</h1>
            </div>
            <div className="col-sm-auto">
              <Link className="btn btn-primary" to={"/admin/tourguides/add"}>
                <i className="tio-user-add mr-1"></i> Thêm hướng dẫn viên
              </Link>
            </div>
          </div>
        </div>
        {/* End Page Header */}

        {/* <!-- Stats --> */}
        <div className="row gx-2 gx-lg-3">
          <div className="col-sm-6 col-lg-3 mb-3 mb-lg-5">
            {/* <!-- Card --> */}
            <div className="card card-hover-shadow h-100">
              <div className="card-body">
                <div className="media align-items-center">
                  <i className="tio-explore-outlined tio-xl text-primary mr-3 ml-3"></i>

                  <div className="media-body ml-2">
                    <span className="d-block font-size-md">Hướng dẫn viên</span>
                    <div className="d-flex align-items-center">
                      <h3 className="mb-0">
                        {tourguideStats ? tourguideStats.tourguideCount : 0}
                      </h3>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            {/* <!-- End Card --> */}
          </div>
        </div>
        {/* <!-- End Stats --> */}

        {/* Table Hướng dẫn viên */}
        <div className="card mb-3 mb-lg-5">
          {/* <!-- Header --> */}
          <div className="card-header">
            <div className="row justify-content-between align-items-center flex-grow-1">
              <div className="col-12 col-md">
                <div className="d-flex justify-content-between align-items-center">
                  <h4 className="card-header-title">Hướng Dẫn Viên</h4>
                </div>
              </div>

              <div className="col-auto">
                {/* <!-- Filter --> */}
                <div className="row align-items-sm-center">
                  <div className="col-md">
                    <CSVLink
                      data={tourguideList.map(({ ...rest }) => rest)}
                      filename={"huong_dan_vien.csv"}
                      className="btn btn-sm btn-primary"
                    >
                      Xuất CSV
                      <i className="tio-open-in-new ml-2"></i>
                    </CSVLink>
                  </div>
                </div>
                {/* <!-- End Filter --> */}
              </div>
            </div>
          </div>
          {/* <!-- End Header --> */}
          <div className="card-body">
            {/* <!-- Input Group --> */}
            <div className="input-group input-group-merge">
              <div className="input-group-prepend">
                <span className="input-group-text">
                  <i className="tio-search"></i>
                </span>
              </div>
              <input
                id="datatableSearch"
                type="search"
                className="form-control"
                placeholder="Tìm hướng dẫn viên"
                aria-label="Tìm hướng dẫn viên"
                value={searchKeywordTourGuide}
                onChange={handleSearchTourGuideChange}
              />
            </div>
            {/* <!-- End Input Group --> */}
          </div>

          {/* <!-- Table --> */}
          <div className="table-responsive datatable-custom">
            <div
              id="datatable_wrapper"
              className="dataTables_wrapper no-footer"
            >
              <table
                id="datatable"
                className="table table-borderless table-thead-bordered table-nowrap table-align-middle card-table dataTable no-footer"
                role="grid"
                aria-describedby="datatable_info"
              >
                <thead className="thead-light">
                  <tr role="row">
                    <th
                      scope="col"
                      className="table-column-pr-0 sorting_disabled"
                      rowSpan="1"
                      colSpan="1"
                      aria-label=""
                    ></th>
                    <th
                      className="table-column-pl-0"
                      rowSpan="1"
                      colSpan="1"
                      aria-label="Họ tên"
                    >
                      Họ tên
                    </th>
                    <th
                      className=""
                      aria-controls="datatable"
                      rowSpan="1"
                      colSpan="1"
                      aria-label=""
                    >
                      Ngày sinh
                    </th>
                    <th
                      className=""
                      aria-controls="datatable"
                      rowSpan="1"
                      colSpan="1"
                      aria-label=""
                    >
                      Sô điện thoại
                    </th>
                    <th
                      className="sorting_disabled"
                      rowSpan="1"
                      colSpan="1"
                      aria-label="Email"
                    >
                      Lương
                    </th>
                    <th
                      className=""
                      aria-controls="datatable"
                      rowSpan="1"
                      colSpan="1"
                      aria-label=""
                    >
                      Email
                    </th>
                    <th
                      className=""
                      aria-controls="datatable"
                      rowSpan="1"
                      colSpan="1"
                      aria-label=""
                    >
                      Thao tác
                    </th>
                  </tr>
                </thead>

                <tbody>
                  {tourguideList &&
                    tourguideList.map((tourguide, index) => (
                      <tr
                        key={`T${tourguide.id}`}
                        role="row"
                        className={`${
                          index % 2 === 1 ? "even-row" : "odd-row"
                        }`}
                      >
                        <td key={index}>{index + 1}</td>
                        <td className="table-column-pl-0 h5">
                          {tourguide.ho} {tourguide.ten}
                        </td>

                        <td>{tourguide.dob}</td>
                        <td>{tourguide.phone}</td>
                        <td>
                          <MoneyFormat amount={tourguide.salary} />
                        </td>
                        <td>{tourguide.email}</td>
                        <td>
                          <Link
                            className="btn btn-primary mx-2"
                            to={`/admin/tourguides/${tourguide.id}`}
                          >
                            Xem
                          </Link>
                        </td>
                      </tr>
                    ))}
                </tbody>
              </table>
            </div>
          </div>
          {/* <!-- End Table --> */}

          {/* <!-- Footer --> */}
          <div className="card-footer">
            {/* <!-- Pagination --> */}
            <div className="row justify-content-center justify-content-sm-between align-items-sm-center">
              <div className="col-sm mb-2 mb-sm-0">
                <div className="d-flex justify-content-center justify-content-sm-start align-items-center">
                  <span className="text-secondary mr-2">
                    Hiển thị: {tourguideList ? tourguideList.length : 0} /{""}{" "}
                    {totalElements}
                    {}
                  </span>
                </div>
              </div>

              <div className="col-sm-auto">
                <div className="d-flex justify-content-center justify-content-sm-end">
                  {/* <!-- Pagination --> */}
                  <nav
                    id="datatablePagination"
                    aria-label="Activity pagination"
                  >
                    <div
                      className="dataTables_paginate paging_simple_numbers"
                      id="datatable_paginate"
                    >
                      <ul
                        id="datatable_pagination"
                        className="pagination datatable-custom-pagination"
                      >
                        <li className="paginate_item page-item ">
                          <div
                            className="paginate_button previous page-link"
                            onClick={handlePrevPage}
                          >
                            <span aria-hidden="true">Trước</span>
                          </div>
                        </li>
                        <li className="paginate_item page-item disabled">
                          <div className="paginate_button page-link">
                            <span aria-hidden="true">
                              {currentPage + 1} / {totalPages}
                            </span>
                          </div>
                        </li>

                        <li className="paginate_item page-item">
                          <div
                            className="paginate_button next page-link"
                            onClick={handleNextPage}
                          >
                            <span aria-hidden="true">Sau</span>
                          </div>
                        </li>
                      </ul>
                    </div>
                  </nav>
                </div>
              </div>
            </div>
            {/* <!-- End Pagination --> */}
          </div>
          {/* <!-- End Footer --> */}
        </div>

        {/* Hết Table Hướng dẫn viên */}
      </div>
    </main>
  );
}