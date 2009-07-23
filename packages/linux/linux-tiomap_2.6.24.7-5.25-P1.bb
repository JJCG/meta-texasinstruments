require linux-omap.inc
inherit ccasefetch

PR = "r0"

COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp"
DEFAULT_PREFERENCE = "1"

CCASE_SPEC = "\
	element /vobs/wtbu/CSSD_L_GIT_2.6/linux/kernel_org/2.6_kernel/... LINUX-GIT-2.6.24K_RLS_5.25-P1%\
	element /vobs/wtbu/CSSD_L_GIT_2.6/linux/kernel_org/2.6_kernel/... LINUX-GIT-2.6.24K_RLS_5.25%\
	element	/vobs/wtbu/CSSD_L_GIT_2.6/linux/kernel_org	/main/LATEST%\
	element	/vobs/wtbu/CSSD_L_GIT_2.6/linux			/main/LATEST%\
	element	/vobs/wtbu/CSSD_L_GIT_2.6			/main/LATEST%\
	element *						/main/0%\
	"
CCASE_PATHFETCH = "/vobs/wtbu/CSSD_L_GIT_2.6/linux/kernel_org/2.6_kernel"
CCASE_PATHCOMPONENTS = 5
CCASE_PATHCOMPONENT = "2.6_kernel"

# You can supply your own defconfig if you like.  See
# http://bec-systems.com/oe/html/recipes_sources.html for a full explanation
#SRC_URI_omap-3430ldp += "file://defconfig-omap-3430ldp"
#SRC_URI_omap-3430sdp += "file://defconfig-omap-3430sdp"

# fix for REQBUFS bug in v4l2 display driver:
SRC_URI += "file://omap24xxvout-reqbufs.patch;patch=1"

# work-around for touchscreen problem (remove this when proper soln is found):
#ADD_DISTRO_FEATURES += "sed -i 's/# CONFIG_INTERCONNECT_IO_POSTING is not set/CONFIG_INTERCONNECT_IO_POSTING=y/' ${S}/.config"

do_stage_append() {
	install -d ${STAGING_KERNEL_DIR}/drivers/media/video/isp
	install -m 0644 ${S}/drivers/media/video/isp/*.h ${STAGING_KERNEL_DIR}/drivers/media/video/isp
	install -d ${STAGING_KERNEL_DIR}/arch/arm/mach-omap2
	install -m 0644 ${S}/arch/arm/mach-omap2/*.h ${STAGING_KERNEL_DIR}/arch/arm/mach-omap2
}
