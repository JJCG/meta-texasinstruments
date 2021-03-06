DEPENDS = "tidspbridge-lib tiopenmax-perf tiopenmax-common"
DESCRIPTION = "Texas Instruments OpenMAX IL Core."
PR = "r0"
COMPONENT_PATH = "system/src/openmax_il/omx_core"

require tiopenmax-modular.inc
require tiopenmax-cspec-${PV}.inc

OMX_COMPILE_TARGET = "core"
OMX_INSTALL_TARGET = "core.install"
OMX_BUILD_DIR = ""

inherit pkgconfig

SRC_URI = "\
	file://libomxil-ti.pc \
	"

do_compile_prepend() {
	install -m 0644 ${FILESDIR}/libomxil-ti.pc ${S}/libomxil.pc
	install -d ${D}${libdir}
}

