import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { searchPackages, getPackageStats } from "../../api/AdminApi";
import MoneyFormat from "../Custom/MoneyFormat";
import { CSVLink } from "react-csv";
export default function Package() {
  const [packageList, setPackageList] = useState([]);
  const [packageStats, setPackageStats] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [totalElements, setTotalElements] = useState(0);

  const [searchKeywordPackage, setSearchKeywordPackage] = useState("");

  useEffect(() => {
    const getPackagesPage = async () => {
      const tmpPackagesPage = await searchPackages(
        null,
        searchKeywordPackage,
        currentPage
      );
      setPackageList(tmpPackagesPage ? tmpPackagesPage.content : []);
      setTotalPages(tmpPackagesPage ? tmpPackagesPage.totalPages : 0);
      setTotalElements(tmpPackagesPage ? tmpPackagesPage.totalElements : 0);
    };
    getPackagesPage();
  }, [currentPage, searchKeywordPackage]);

  useEffect(() => {
    const fetchPackagesStats = async () => {
      const tmpPackagesStats = await getPackageStats();
      setPackageStats(tmpPackagesStats);
    };
    fetchPackagesStats();
  }, []);

  const handleNextPage = () => {
    setCurrentPage(
      currentPage + 1 < totalPages ? currentPage + 1 : currentPage
    );
  };

  const handlePrevPage = () => {
    setCurrentPage(currentPage > 0 ? currentPage - 1 : 0);
  };

  const handleSearchPackageChange = (event) => {
    setCurrentPage(0);
    setSearchKeywordPackage(event.target.value);
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
              <h1 className="page-header-title">Gói</h1>
            </div>
            <div className="col-sm-auto">
              <Link className="btn btn-primary" to={"/admin/packages/add"}>
                <i className="tio-file-add mr-1"></i> Thêm gói
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
                  <i className="tio-folder tio-xl text-primary mr-3 ml-3"></i>

                  <div className="media-body ml-2">
                    <span className="d-block font-size-md">Gói</span>
                    <div className="d-flex align-items-center">
                      <h3 className="mb-0">
                        {packageStats ? packageStats.packagesCount : 0}
                      </h3>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            {/* <!-- End Card --> */}
          </div>
          <div className="col-sm-6 col-lg-3 mb-3 mb-lg-5">
            {/* <!-- Card --> */}
            <div className="card card-hover-shadow h-100">
              <div className="card-body">
                <div className="media align-items-center">
                  <i className="tio-unarchive tio-xl text-primary mr-3 ml-3"></i>

                  <div className="media-body ml-2">
                    <span className="d-block font-size-md">Còn trống</span>
                    <div className="d-flex align-items-center">
                      <h3 className="mb-0">
                        {packageStats ? packageStats.available : 0}
                      </h3>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            {/* <!-- End Card --> */}
          </div>
          <div className="col-sm-6 col-lg-3 mb-3 mb-lg-5">
            {/* <!-- Card --> */}
            <div className="card card-hover-shadow h-100">
              <div className="card-body">
                <div className="media align-items-center">
                  <i className="tio-time tio-xl text-primary mr-3 ml-3"></i>

                  <div className="media-body ml-2">
                    <span className="d-block font-size-md">Sắp diễn ra</span>
                    <div className="d-flex align-items-center">
                      <h3 className="mb-0">
                        {packageStats ? packageStats.upcoming : 0}
                      </h3>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            {/* <!-- End Card --> */}
          </div>
          <div className="col-sm-6 col-lg-3 mb-3 mb-lg-5">
            {/* <!-- Card --> */}
            <div className="card card-hover-shadow h-100">
              <div className="card-body">
                <div className="media align-items-center">
                  <i className="tio-timer-off tio-xl text-primary mr-3 ml-3"></i>

                  <div className="media-body ml-2">
                    <span className="d-block font-size-md">Đã kết thúc</span>
                    <div className="d-flex align-items-center">
                      <h3 className="mb-0">
                        {packageStats ? packageStats.finished : 0}
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

        {/* Table Package */}
        <div className="card mb-3 mb-lg-5">
          {/* <!-- Header --> */}
          <div className="card-header">
            <div className="row justify-content-between align-items-center flex-grow-1">
              <div className="col-12 col-md">
                <div className="d-flex justify-content-between align-items-center">
                  <h4 className="card-header-title">Gói</h4>
                </div>
              </div>

              <div className="col-auto">
                {/* <!-- Filter --> */}
                <div className="row align-items-sm-center">
                  <div className="col-md">
                    <CSVLink
                      data={packageList.map(
                        ({ description, slug, ...rest }) => rest
                      )}
                      filename={"goi_tour.csv"}
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
                placeholder="Tìm gói"
                value={searchKeywordPackage}
                onChange={handleSearchPackageChange}
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
                      aria-label="Tên"
                    >
                      Tên
                    </th>

                    <th
                      className=""
                      tabIndex="0"
                      rowSpan="1"
                      colSpan="1"
                      aria-label="Địa chỉ"
                    >
                      Còn trống
                    </th>
                    <th
                      className=""
                      tabIndex="0"
                      rowSpan="1"
                      colSpan="1"
                      aria-label="Tỉnh"
                    >
                      Giá(vnđ)
                    </th>
                    <th
                      className="sorting_disabled"
                      rowSpan="1"
                      colSpan="1"
                      aria-label="Hạng"
                    >
                      Ngày bắt đầu
                    </th>
                    <th
                      className=""
                      tabIndex="0"
                      rowSpan="1"
                      colSpan="1"
                      aria-label="Giá"
                    >
                      Ngày kết thúc
                    </th>
                    <th
                      className=""
                      tabIndex="0"
                      rowSpan="1"
                      colSpan="1"
                      aria-label=""
                    >
                      Thao tác
                    </th>
                  </tr>
                </thead>

                <tbody>
                  {packageList &&
                    packageList.map((packages, index) => (
                      <tr
                        role="row"
                        key={`P${packages.id}`}
                        className={`${
                          index % 2 === 1 ? "even-row" : "odd-row"
                        }`}
                      >
                        <td key={index}>{index + 1}</td>
                        <td className="table-column-pl-0 h5">
                          <p>{packages.name}</p>
                        </td>
                        <td>
                          {packages.available} / {packages.capacity}
                        </td>
                        <td>
                          <MoneyFormat amount={packages.cost} />
                        </td>
                        <td>{packages.startDate}</td>
                        <td>{packages.endDate}</td>
                        <td>
                          <Link
                            className="btn btn-primary mx-2"
                            to={`/admin/packages/${packages.id}`}
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
                    Hiển thị: {packageList ? packageList.length : 0} /{""}{" "}
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

        {/* Hết Table Packages */}
      </div>
    </main>
  );
}
