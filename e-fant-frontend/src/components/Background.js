import React, { useEffect } from "react";

const Background = () => {
    const setVideoSize = () => {
        const vidWidth = 1920;
        const vidHeight = 1080;
        const windowWidth = window.innerWidth;
        const windowHeight = window.innerHeight;
        const tempVidWidth = windowHeight * vidWidth / vidHeight;
        const tempVidHeight = windowWidth * vidHeight / vidWidth;
        const newVidWidth = tempVidWidth > windowWidth ? tempVidWidth : windowWidth;
        const newVidHeight = tempVidHeight > windowHeight ? tempVidHeight : windowHeight;
        const tmVideo = document.getElementById("tm-video");


        if (tmVideo) {
            tmVideo.style.width = `${newVidWidth}px`;
            tmVideo.style.height = `${newVidHeight}px`;
        }

    };

    const openTab = (evt, id) => {
        document.querySelectorAll(".tm-tab-content").forEach((tabContent) => {
            tabContent.style.display = "none";
        });
        document.querySelectorAll(".tm-tab-link").forEach((tabLink) => {
            tabLink.classList.remove("active");
        });

        const tabContent = document.getElementById(id);
        if (tabContent) {
            tabContent.style.display = "block";
        }

        evt.currentTarget.classList.add("active");
    };

    useEffect(() => {
        // Initialization logic when the component mounts
        // eslint-disable-next-line no-restricted-globals
        let pageId = location.hash;
        const activePageLink = document.querySelector(".tm-page-link.active");
        const defaultPageId = activePageLink ? activePageLink.getAttribute("href") : null;

        if (pageId) {
            const menuItem = document.querySelector(`.tm-page-link[href^="${pageId}"]`);
            if (menuItem) {
                highlightMenu(menuItem);
                const page = document.getElementById(pageId);
                if (page) {
                    showPage(page);
                }
            }
        } else if (defaultPageId) {
            const defaultPage = document.getElementById(defaultPageId);
            if (defaultPage) {
                showPage(defaultPage);
            }
        }

        setVideoSize();

        // Set video background size based on window size
        let timeout;
        window.onresize = function() {
            clearTimeout(timeout);
            timeout = setTimeout(setVideoSize, 100);
        };

        // Play/Pause button for video background
        const btn = document.getElementById("tm-video-control-button");

        btn.addEventListener("click", function(e) {
            const video = document.getElementById("tm-video");
            this.classList.remove();

            if (video.paused) {
                video.play();
                this.classList.add("fas", "fa-pause");
            } else {
                video.pause();
                this.classList.add("fas", "fa-play");
            }
        });
    }, []);

    const highlightMenu = (menuItem) => {
        document.querySelectorAll(".tm-page-link").forEach((link) => {
            link.classList.remove("active");
        });
        menuItem.classList.add("active");
    };

    const showPage = (page) => {
        document.querySelectorAll(".tm-page-content").forEach((content) => {
            content.style.display = "none";
        });
        page.style.display = "block";
    };

    return null;
};

export default Background;
